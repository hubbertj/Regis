from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

db = SQLAlchemy()


class Registrants(db.Model):
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
        super(Registrants, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Registrants %r>' % self.id

    def seed(self):
        # seeds table with data from csv
        pass
