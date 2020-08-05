from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

db = SQLAlchemy()


class Workshops(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(80), unique=True, nullable=False)
    session_number = db.Column(db.Integer, nullable=False)
    room_number = db.Column(db.String(80), unique=False, nullable=False)
    start_time = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    end_time = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    def __init__(self, **kwargs):
        super(Workshops, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Workshops %r>' % self.title

    def seed(self):
        # seeds table with data from csv
        pass
