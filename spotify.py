from __future__ import print_function    # (at top of module)
from spotipy.oauth2 import SpotifyClientCredentials
import json
import spotipy
import time
import sys



#for line in sys.stdin:
client_credentials_manager = SpotifyClientCredentials(client_id='80e38aa27ddb4a008de582ef074390c6', client_secret='f7a901af79724993bb6cfe5f6d70ac1b')
sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)
# tracks = sp.search('track:' + 'Dark Knight Dummo Trippie Redd', type='track')
# print(tracks['tracks'])
# track_id = tracks['tracks']['items'][0]['id']
# print(track_id)
# sp.trace=True

# # need to consider the cases where you don't find a track matching the query 

# response = sp.audio_features(['spotify:track:' + track_id])
# print()
# print()
# print(response)
# valence = response[0]['valence']
#print(valence)

for line in sys.stdin:
  line = line.rstrip()
  (date, title, artist, weeks, rank) = line.split('|')
  tracks = sp.search('track:' + title + " " + artist, type='track')
  if len(tracks['tracks']['items']) == 0: # try searching without the artist when nothing is found
    tracks = sp.search('track:' + title, type='track')
  if len(tracks['tracks']['items']) == 0: # skip if we don't get any results again
    next
  track_id = tracks['tracks']['items'][0]['id'] # get the first result
  response = sp.audio_features(['spotify:track:' + track_id])
  valence = response[0]['valence']
  print(date + "|" + \
        title + "|" + \
        artist + "|" + \
        weeks + "|" + \
        rank + "|" + \
        str(valence))