require 'date'
require 'pp'

Song = Struct.new(:date, :title, :artist, :weeks, :rank, :valence)

class Array
  def average
    inject(&:+) / size
  end
end

class SongAnalyzer
  attr_accessor :songs
  def initialize
    @songs = []
    ARGF.each_line do |song_line|
      next if song_line =~ /retrying/
      (date, title, artist, weeks, rank, valence) =
        song_line.chomp.split("|")
      @songs << Song.new.tap do |song|
        song.date    = Date.strptime(date, "%Y-%m-%d")
        song.title   = title
        song.artist  = artist
        song.weeks   = weeks.to_i
        song.rank    = rank.to_i
        song.valence = valence.to_f
      end
    end
  end
end
"""
song_analyzer
  .songs
  .group_by { |song| song.date.year }
  .map { |year, songs|
    [
      year,
      songs
        .group_by { |song| song.date.month }
        .map { |month, songs_list|
          [
            month,
            songs_list.group_by(&:title)
          ]
        }.to_h
    ]
  }.to_h
"""

"""
collection[2018][1].map do |song_title, songs|
  Song.new.tap do |s|
    s.title   = song_title
    s.artist  = songs.first.artist
    s.weeks   = songs.max_by { |sw| sw.weeks }.weeks
    s.rank    = songs.map { |sr| sr.rank }.average
    s.date    = songs.first.date
    s.valence = songs.first.valence
  end
end
"""

song_analyzer = SongAnalyzer.new

song_collector =
  song_analyzer
    .songs
    .group_by { |song| song.date.year }
    .map { |year, songs|
      [
        year,
        songs
          .group_by { |song| song.date.month }
          .map { |month, songs_list|
            [
              month,
              songs_list
                .group_by(&:title)
                .map do |song_title, songs|
                  Song.new.tap do |s|
                    s.title   = song_title
                    s.artist  = songs.first.artist
                    s.weeks   = songs.max_by { |sw| sw.weeks }.weeks
                    s.rank    = songs.map { |sr| sr.rank }.average
                    s.date    = songs.first.date
                    s.valence = songs.first.valence
                  end
                end
            ]
          }
          .to_h ##
          .map { |month, songs_list|
            [
              month,
              songs_list
                .map { |s| (s.weeks**2) * s.valence + s.rank * s.valence }
                .average
            ]
          }
          .to_h
      ]
    }.to_h # year, month, valence

points =
  song_collector
  .map { |year, songs_by_month|
    [year, songs_by_month.sort_by { |month, _| month }.to_h]
  }
  .to_h
  .sort_by { |year, _| year }
  .to_h
  .map { |year, songs_by_month|
    [year, songs_by_month.to_a]
  }
  .to_h
  .flat_map { |year, songs_by_month|
    [year].product(songs_by_month)
  }
  .map { |(year, (month, average_valence))| [year, month, average_valence] }
  .map.with_index { |array, index| [index] + array }

points.each { |e| puts e[0].to_s + "," + e[3].to_s }