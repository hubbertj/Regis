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


:- initialization(main).
main :- write('Hello World!'), nl, halt.