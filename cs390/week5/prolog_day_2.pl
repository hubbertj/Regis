%% Reverse the elements in a list.
reverse(X, X, []).
reverse(X, Y, [A|Z]) :-
	reverse(X, [A|Y], Z).

%% Find the smallest element in a list.
small(X, [Head|Tail]) :-
	small(X, Head, Tail).

small(X, X, []).
small(X, Smallest, [A|B]) :-
	Smaller is min(A, Smallest),
	small(X, Smaller, B).

%% Sort the elements of a list
sort_list(List, SortedList):-
	sort_list(List,[],SortedList).

sort_list([], X, X).
sort_list([Head|Tail], Sort, SortedList):-
	bubbleSortMax(Head, Tail, NewList, Max),
	sort_list(NewList, [Max|Sort], SortedList).

%% bubble sort gets the max from the list  
bubbleSortMax(X,[],[],X).
bubbleSortMax(X,[Head|T],[Head|Tail],Max):-
	X > Head,
	bubbleSortMax(X,T,Tail,Max).
bubbleSortMax(X,[Head|T],[X|Tail],Max):-
	X =< Head,
	bubbleSortMax(Head,T,Tail,Max).


main:-
  write('Making a list'), nl,
  List = [5, 9, 12, 15, 27, 28, 33, 37, 45, 46, 49, 52, 70, 74, 76, 78, 79, 81, 83, 95],
  write(List), nl, nl,
  write('Reverse the elements in a list'), nl,
  reverse(Reverse_list, [], List),
  write(Reverse_list), nl, nl,
  write('Find the smallest element in a list'), nl,
  small(Small_element, List),
  write(Small_element), nl, nl,
  write('Sort the elements of a list'), nl,
  sort_list(List, SortedList),
  write(SortedList), nl, nl,
  write('End'), nl, nl.

%% runs main
:- initialization main.

%% removes from prolog when done testing
:- abolish(counter/1).

