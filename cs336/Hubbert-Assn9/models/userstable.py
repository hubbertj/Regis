from flask_sqlalchemy import SQLAlchemy
db = SQLAlchemy()


class Users(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(120), unique=True, nullable=False)
    firstname = db.Column(db.String(120), nullable=False)
    lastname = db.Column(db.String(120), nullable=False)
    password = db.Column(db.String(120), nullable=False)

    def __init__(self, **kwargs):
        super(Users, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Users %r>' % self.username

    def seed(self):
        # seeds table with data from csv
        pass

