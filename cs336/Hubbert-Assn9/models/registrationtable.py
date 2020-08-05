# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Model for Nominees Registrants

from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
from . import environ
import csv

db = SQLAlchemy()


class Registrant(db.Model):
    __tablename__ = 'registrants'
    id = db.Column(db.Integer, primary_key=True)
    date = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    title = db.Column(db.String(80), unique=False, nullable=False)
    firstname = db.Column(db.String(80), unique=False, nullable=False)
    lastname = db.Column(db.String(80), unique=False, nullable=False)
    address1 = db.Column(db.String(120), unique=False, nullable=False)
    address2 = db.Column(db.String(120), unique=False, nullable=True)
    city = db.Column(db.String(80), unique=False, nullable=False)
    state = db.Column(db.String(80), unique=False, nullable=False)
    zipcode = db.Column(db.String(25), unique=False, nullable=False)
    phone = db.Column(db.String(25), unique=False, nullable=False)
    email = db.Column(db.String(120), unique=False, nullable=False)
    web = db.Column(db.String(120), unique=False, nullable=False)
    job_title = db.Column(db.String(80), unique=False, nullable=False)
    company = db.Column(db.String(120), unique=False, nullable=False)
    meal_pack = db.Column(db.Boolean, default=False, nullable=False)
    billing_firstname = db.Column(db.String(80), unique=False, nullable=False)
    billing_lastname = db.Column(db.String(80), unique=False, nullable=False)
    card_type = db.Column(db.String(25), unique=False, nullable=False)
    card_number = db.Column(db.String(16), unique=False, nullable=False)
    sid = db.Column(db.String(5), unique=False, nullable=False)
    exp_year = db.Column(db.String(5), unique=False, nullable=False)
    exp_month = db.Column(db.String(5), unique=False, nullable=False)
    session1 = db.Column(db.String(80), unique=False, nullable=True)
    session2 = db.Column(db.String(80), unique=False, nullable=True)
    session3 = db.Column(db.String(80), unique=False, nullable=True)

    def __init__(self, **kwargs):
        super(Registrant, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Registrants %r>' % self.id

    def seed(self):
        seed_file = self.__class__.__name__.lower() + '_data.csv'
        seed_list = None
        try:
            with open(environ.get('SEED_DIR') + '\\' + seed_file, 'r', encoding='utf-8') as file:
                reader = csv.reader(file)
                seed_list = list(reader)
        except FileNotFoundError:
            print(seed_file + " seed file wasn't found")

        if seed_list is not None:
            print(len(seed_list))
