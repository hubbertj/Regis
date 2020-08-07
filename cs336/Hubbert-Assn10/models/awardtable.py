# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Model for Nominees Nominees

from flask_sqlalchemy import SQLAlchemy
import csv
from . import environ

db = SQLAlchemy()


class Nominee(db.Model):
    __tablename__ = 'nominees'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), unique=False, nullable=False)
    description = db.Column(db.String(120), unique=False, nullable=True)
    image_name = db.Column(db.String(120), unique=False, nullable=False)
    vote_count = db.Column(db.Integer)

    def __init__(self, **kwargs):
        super(Nominee, self).__init__(**kwargs)
        # do custom initialization here

    @property
    def serialized(self):
        return {
            'id': self.id,
        }

    def __repr__(self):
        return '<Nominee %r>' % self.name

    def seed(self):
        class_name = self.__class__.__name__
        seed_file = 'awards.csv'
        seed_list = None
        try:
            with open(environ.get('SEED_DIR') + '\\' + seed_file, 'r', encoding='utf-8') as file:
                reader = csv.reader(file)
                seed_list = list(reader)
        except FileNotFoundError:
            print(seed_file + " seed file wasn't found")

        if seed_list is not None:
            db.session.query(Nominee).delete()
            db.session.commit()
            for row in seed_list:
                nominee = Nominee(name=row[0],
                                  description=row[1],
                                  image_name=row[2],
                                  vote_count=row[3], )
                db.session.add(nominee)
                db.session.commit()
