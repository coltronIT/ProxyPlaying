from time import sleep
from flask import (
    Flask,
    jsonify,
)
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


@app.route('/api/all_songs')
def get_song_data():
    sleep(5)
