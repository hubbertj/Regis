from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


class Nominees(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), unique=False, nullable=False)
    description = db.Column(db.String(120), unique=False, nullable=True)
    image_name = db.Column(db.String(120), unique=False, nullable=False)
    vote_count = db.Column(db.Integer)

    def __init__(self, **kwargs):
        super(Nominees, self).__init__(**kwargs)
        # do custom initialization here

    def __repr__(self):
        return '<Nominees %r>' % self.name

    def seed(self):
        # seeds table with data from csv
        pass
