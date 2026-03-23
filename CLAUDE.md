# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

Academic coursework repository from Regis University. Each top-level folder is a course and contains syllabi, PDFs, weekly assignment docs, and any code written for that course.

Courses with code:

| Folder | Language(s) |
|---|---|
| `CS310 - Data Structures/` | Java (NetBeans/Ant) |
| `CS336 - Web and Database Applications/` | Python/Flask + AngularJS |
| `CS370 - Assembly Language/` | x86-64 NASM (macOS) |
| `CS390 - Prin Programming Languages/` | Ruby, Haskell, Prolog |
| `CS431 - Operating Systems Analysis-Design/` | C |
| `CS473 - Artificial Intelligence/` | Java (AIMA framework) |
| `CS475 - Computation Theory/` | (assignments/docs only) |

`MS-DataScience-Application/` contains application essays for the Regis MS Data Science program (not coursework).

---

## Build & Run Commands

### Java (CS310, CS473) — Ant/NetBeans

```bash
# From any CS310 assignment directory, e.g.:
cd "CS310 - Data Structures/CS310HubbertAssn6"
ant build       # compile and package JAR
ant run         # run the main class
ant clean       # remove build artifacts
ant javadoc     # generate Javadoc in doc/
```

All CS310 assignments read from `input/` and write reports to `output/`.

For CS473 AIMA (inside `CS473 - Artificial Intelligence/Week1/`):
```bash
cd "CS473 - Artificial Intelligence/Week1/aima-core"
ant -f build.xml aima-core.compile-all
ant -f build.xml aima-core.test-all
ant -f build.xml aima-core.release    # produces aima-core.jar
```

### Python/Flask (CS336 Assn8–10)

```bash
cd "CS336 - Web and Database Applications/Hubbert-Assn10"
cp .env.template .env    # then edit .env
python app.py            # runs on port defined in .env (default 3000)

# Generate name tags
python scripts/name_tags.py
```

Key `.env` variables: `FLASK_DEBUG`, `PORT`, `FLASK_ENV`, `SECRET_KEY`, `SQLALCHEMY_DATABASE_URI`, `SEED` (set to 1 to seed DB on startup), `SEED_DIR`.

### C (CS431)

```bash
cd "CS431 - Operating Systems Analysis-Design/Week 1"
gcc -o mycopy mycopy.c
./mycopy <source> <dest>
```

### Assembly (CS370)

```bash
cd "CS370 - Assembly Language/hello_world"
# 64-bit macOS
nasm -f macho64 64.asm
ld -macosx_version_min 10.7.0 -lSystem -o 64 64.o
./64
```

### Ruby (CS390)

```bash
ruby "CS390 - Prin Programming Languages/week1/helloWorld.rb"
ruby "CS390 - Prin Programming Languages/week2/day_2.rb"
```

### Haskell (CS390)

```bash
cd "CS390 - Prin Programming Languages/week6"
cabal build
```

### Prolog (CS390)

```bash
swipl "CS390 - Prin Programming Languages/week3/day_3.pl"
swipl "CS390 - Prin Programming Languages/week5/prolog_day_2.pl"
```

---

## CS310 Architecture

Each assignment builds on the last, adding a new data structure while reusing `Realtor`, `Property`, `PrintImpl`, and `RealtorLogImpl`. Code lives in `CS310 - Data Structures/`:

| Subfolder | New Structure |
|---|---|
| `CS310HubbertAssn3/` | Singly linked list (`RealtorNode`) |
| `CS310HubbertAssn4/` | Stack (`CarStackImpl`) and Queue (`RealtorQueueImpl`) |
| `CS310HubbertAssn5/` | Hash map (`PropertyLogImpl.MapEntry`) |
| `CS310HubbertAssn6/` | Binary search tree (`BinaryTree`, `TreeNode`) |

`CS310Hubbert.java` is always the main entry point. It reads delimited text files from `input/`, delegates to `*LogImpl` classes, then calls `PrintImpl` to write reports to `output/`.

## CS336 Flask Architecture

Assignments 8–10 use a Flask app with these layers:

- `app.py` — application factory, registers blueprints, initializes DB
- `routes.py` — page route blueprint
- `api.py` — REST API blueprint (JSON responses for AngularJS controllers)
- `auth.py` — login/logout blueprint using Flask-Login
- `models/` — one SQLAlchemy model file per table (`userstable.py`, `workshoptable.py`, `registrationtable.py`, `awardtable.py`); each module owns its own `db` instance, initialized via `db.init_app(app)`
- `templates/` — Jinja2 HTML (extends `base.html`)
- `js/controllers/` — AngularJS controllers that consume the REST API

The frontend is an AngularJS single-page app (`js/conference.js` defines the module and routes); each page has a corresponding controller in `js/controllers/`.

## CS473 AIMA Architecture

Multi-module Ant project inside `CS473 - Artificial Intelligence/Week1/`:
- `aima-core/` — algorithm implementations (search, logic, learning, probability, NLP)
- `aima-gui/` — Swing GUI visualizations
- `aimax-osm/` — OpenStreetMap integration
- `CleaningAgent/` — custom vacuum-world agent built on top of aima-core

Algorithm packages under `aima.core`: `search`, `logic`, `learning`, `probability`, `nlp`, `environment.*`.
