# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Model for Nominees Registrants

from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
from . import environ
import csv
import uuid

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
    email = db.Column(db.String(120), unique=True, nullable=False)
    web = db.Column(db.String(120), unique=False, nullable=False)
    job_title = db.Column(db.String(80), unique=False, nullable=False)
    company = db.Column(db.String(120), unique=False, nullable=False)
    meal_pack = db.Column(db.String(80), default=False, nullable=False)
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
    confirmation = db.Column(db.String(120), unique=True, nullable=False)

    @property
    def serialized(self):
        return {
            'id': self.id,
            'date': self.date.isoformat(),
            'title': self.title,
            'firstname': self.firstname,
            'lastname': self.lastname,
            'email': self.email,
            'address1': self.address1,
            'address2': self.address2,
            'city': self.city,
            'state': self.state,
            'zipcode': self.zipcode,
            'phone': self.phone,
            'web': self.web,
            'job_title': self.job_title,
            'company': self.company,
            'meal_pack': self.meal_pack,
            'billing_firstname': self.billing_firstname,
            'billing_lastname': self.billing_lastname,
            'card_type': self.card_type,
            'card_number': self.card_number[-4:].rjust(len(self.card_number), "*"),
            'sid': 'xxx',
            'exp_year': self.exp_year,
            'exp_month': self.exp_month,
            'session1': self.session1,
            'session2': self.session2,
            'session3': self.session3,
            'confirmation': self.confirmation,
        }

    def __init__(self, **kwargs):
        super(Registrant, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Registrant %r>' % self.id

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
            for row in seed_list:
                if row and len(row) >= 10:
                    registrant = Registrant.query.filter_by(email=row[10]).first()
                    if registrant is None:
                        registrant = Registrant(
                            date=datetime.strptime(row[0], '%Y-%m-%d'),
                            title=row[1],
                            firstname=row[2],
                            lastname=row[3],
                            address1=row[4],
                            address2=row[5],
                            city=row[6],
                            state=row[7],
                            zipcode=row[8],
                            phone=row[9],
                            email=row[10],
                            web=row[11],
                            job_title=row[12],
                            company=row[13],
                            meal_pack=row[14],
                            billing_firstname=row[15],
                            billing_lastname=row[16],
                            card_type=row[17],
                            card_number=row[18],
                            sid=row[19],
                            exp_year=row[20],
                            exp_month=row[21],
                            session1=row[22],
                            session2=row[23],
                            session3=row[24],
                            confirmation=str(uuid.uuid4()),
                        )
                        db.session.add(registrant)
                        db.session.commit()
