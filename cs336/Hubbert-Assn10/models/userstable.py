# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Model for Nominees Users

from flask_sqlalchemy import SQLAlchemy
from flask_login import UserMixin
from . import environ
import csv
from werkzeug.security import generate_password_hash

db = SQLAlchemy()


class User(UserMixin, db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(120), unique=True, nullable=False)
    firstname = db.Column(db.String(120), nullable=False)
    lastname = db.Column(db.String(120), nullable=False)
    password = db.Column(db.String(120), nullable=False)

    def __init__(self, **kwargs):
        super(User, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Users %r>' % self.username

    def seed(self):
        seed_file = self.__class__.__name__.lower() + 's.csv'
        seed_list = None
        try:
            with open(environ.get('SEED_DIR') + '\\' + seed_file, 'r', encoding='utf-8') as file:
                reader = csv.reader(file)
                seed_list = list(reader)
        except FileNotFoundError:
            print(seed_file + " seed file wasn't found")

        if seed_list is not None:
            db.session.query(User).delete()
            db.session.commit()
            for row in seed_list:
                user = User(username=row[0],
                            firstname=row[1],
                            lastname=row[2],
                            password=generate_password_hash(row[3]),)
                db.session.add(user)
                db.session.commit()
