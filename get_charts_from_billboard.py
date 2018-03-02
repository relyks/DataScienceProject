import sys
import billboard
from datetime import date
from datetime import datetime

def get_date_obj_from_str(s):
  return datetime.strptime(s, "%Y-%m-%d").date()

start_date = sys.argv[1]
end_date = sys.argv[2]

chart = billboard.ChartData('hot-100', date = end_date)

start_date_obj = get_date_obj_from_str(start_date)
end_date_obj = get_date_obj_from_str(end_date)
previous_date_obj = end_date_obj

while previous_date_obj > start_date_obj:
  for song in chart.entries:
    artist = song.artist
    if " featuring " in song.artist.lower():
      end_of_first_artist_index = song.artist.lower().find(" featuring ")
      artist = song.artist[0:end_of_first_artist_index]
    print(chart.date + "|" + \
          song.title + "|" + \
          artist + "|" + \
          str(song.weeks) + "|" + \
          str(song.rank))
  chart = billboard.ChartData('hot-100', chart.previousDate)
  previous_date_obj = get_date_obj_from_str(chart.previousDate)