Hanoi
====================

Here's my java solution to question 3.4 from Cracking The Coding Interview - aka Towers of Hanoi.  

The trick is to use recursion.  I wasted a lot of time reading general strategies to the game, including wikipedia ( odd vs. even, moving triplets, etc. ).

Example output when running against a disc-count of 3:

	Step (1): Move disc-1 from tower "A" to tower "B".
	Step (2): Move disc-2 from tower "A" to tower "C".
	Step (3): Move disc-1 from tower "B" to tower "C".
	Step (4): Move disc-3 from tower "A" to tower "B".
	Step (5): Move disc-1 from tower "C" to tower "A".
	Step (6): Move disc-2 from tower "C" to tower "B".
	Step (7): Move disc-1 from tower "A" to tower "B".
	Step (8): Move disc-4 from tower "A" to tower "C".
	Step (9): Move disc-1 from tower "B" to tower "C".
	Step (10): Move disc-2 from tower "B" to tower "A".
	Step (11): Move disc-1 from tower "C" to tower "A".
	Step (12): Move disc-3 from tower "B" to tower "C".
	Step (13): Move disc-1 from tower "A" to tower "B".
	Step (14): Move disc-2 from tower "A" to tower "C".
	Step (15): Move disc-1 from tower "B" to tower "C".

