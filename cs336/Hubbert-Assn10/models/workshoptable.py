# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Model for Workshops Table

from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
from . import environ
import csv

db = SQLAlchemy()


class Workshop(db.Model):
    __tablename__ = 'workshops'
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(80), nullable=False)
    session_number = db.Column(db.Integer, nullable=False)
    room_number = db.Column(db.String(80), unique=False, nullable=False)
    start_time = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    end_time = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

    def __init__(self, **kwargs):
        super(Workshop, self).__init__(**kwargs)
        # do custom initialization here

    @property
    def serialized(self):
        return {
            'id': self.id,
            'title': self.title,
            'session_number': self.session_number,
            'room_number': self.room_number,
            'start_time': self.start_time,
            'end_time': self.end_time,
        }

    def __repr__(self):
        return '<Workshops %r>' % self.title

    def seed(self):
        class_name = self.__class__.__name__.lower()
        seed_file = 'workshops.csv'
        seed_list = None
        try:
            with open(environ.get('SEED_DIR') + '\\' + seed_file, 'r', encoding='utf-8') as file:
                reader = csv.reader(file)
                seed_list = list(reader)
        except FileNotFoundError:
            print(seed_file + " seed file wasn't found")

        if seed_list is not None:
            db.session.query(Workshop).delete()
            db.session.commit()
            for row in seed_list:
                workshop = Workshop(
                    title=row[0],
                    session_number=row[1],
                    room_number=row[2],
                    start_time=datetime.strptime(row[3], '%m-%d-%Y %H:%M%p'),
                    end_time=datetime.strptime(row[4], '%m-%d-%Y %H:%M%p'),
                )
                db.session.add(workshop)
                db.session.commit()
