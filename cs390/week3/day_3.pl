%% Codify the following rules into a Prolog knowledge base.
%% 	Pam is the parent of bob
%% 	tom is the parent of bob
%% 	tom is the parent of liz
%% 	bob is the parent of ann
%% 	bob is the parent of pat
%% 	pat is the parent of Jim

%% Run a number of queries against the knowledge base to verify its accuracy.

%% Add an offspring rule to the knowledge base;
%% offspring( Y, X ) :- parent( X, Y ).

%% Run a number of queries to verify the accuracy of the offspring rule.
%% Again, run a number of queries against the knowledge base to verify its accuracy.

%% The Prolog variable Y in the expression  parent( tom, X ), parent( X, Y ) will yield Tom’s grandchildren. 

%% Generalize this expression and create a rule in the knowledge base to identify grandparents.

%% Run a number of queries against the knowledge base to verify its accuracy.
%% Append gender rules to the knowledge base to specify the gender of each person.
%% 	Pam is female
%% 	bob is male
%% 	tom is male
%% 	liz is female
%% 	ann is female
%% 	pat is female
%% 	Jim is male
%% Again, run a number of queries against the knowledge base to verify its accuracy.
%% Create a “mother rule” in the knowledge base that determines which “atoms” have female parents.
%% Run a number of queries against the knowledge base to verify its accuracy.


%% parent facts parent(Parent, Child)
parent(robert, tom).
parent(molly, tom).

parent(john, pam).
parent(kathy, pam).

parent(pam, bob).
parent(tom, bob).

parent(bob, ann).
parent(bob, pat).
parent(tom, liz).

parent(pat, jim).

%% gender facts gender(Person, Gender Type)
gender(robert, male).
gender(john, male).
gender(bob, male).
gender(tom, male).
gender(jim, male).
gender(robert, male).
gender(john, male).

gender(molly, female).
gender(kathy, female).
gender(pam, female).
gender(liz, female).
gender(ann, female).
gender(pat, female).
gender(molly, female).
gender(kathy, female).


offspring(Child, Parent) :- 
\+(Child = Parent), 
parent(Parent, Child).

grandparent(GrandParent, Child) :- 
\+(GrandParent = Child), 
parent(Parent, Child), 
parent(GrandParent, Parent),
write(GrandParent), write('\'s grandchild is '), write(Child), nl.


mother(Mother, Child) :- 
\+(Mother = Child), 
gender(Mother, female),
parent(Mother, Child),
write(Mother), write(' is the mother of '), write(Child), nl.

motherIs(Child) :- 
parent(Parent, Child), 
gender(Parent, female), 
write(Child), write('\'s mother is '), write(Parent), nl.

%% removes from prolog when done testing
:- abolish(counter/1).

%% query

%% list everyones parents
%% parent(Parent, robert).
%% parent(Parent, molly).
%% parent(Parent, john).
%% parent(Parent, kathy).
%% parent(Parent, tom).
%% parent(Parent, bob).
%% parent(Parent, ann).
%% parent(Parent, pat).
%% parent(Parent, liz).
%% parent(Parent, jim).

%% check for offspring
%% offspring(jim, pat).
%% offspring(pam, kathy).
%% offspring(tom, robert).
%% offspring(bob, kathy).

%% list grandparent
%% grandparent(robert, bob).
%% grandparent(molly, bob).
%% grandparent(john, bob).
%% grandparent(pam, ann).

%% check for mothers
%% mother(robert, tom).
%% mother(molly, tom).
%% mother(pam, bob).
%% mother(liz, tom).






