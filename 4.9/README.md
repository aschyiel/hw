Question 4.9:
====================

You are given a binary tree in which each node contains a value.
Design an algorithm to print all paths which sum to a given value.
The path does not need to start or end at the root or a leaf.

My solution at a high-level:
---------------------

Use DP, tally up each path via map-matrix, only print the exact paths later.
This is different than the answer in the book which doesn't allow paths that go "across" the tree. ie. left->parent->right.

Output:
---------------------

Below is an example output of my program. It creates a random tree of numbers and tries to find all the paths that add up to 21 (blackJack).  NOTE: I left dupe paths in there because they're chiral.

  .   .   .   /---{I:83}
  .   .   .   .   \---{U:61}
  .   .   /---{F:44}
  .   .   .   .   .   /---{X:-78}
  .   .   .   .   /---{K:52}
  .   .   .   \---{J:-95}
  .   /---{B:-9}
  .   .   .   /---{Q:-68}
  .   .   .   .   .   /---{D2:15}
  .   .   .   .   \---{Z:94}
  .   .   \---{M:37}
  .   .   .   \---{N:44}
  .   .   .   .   \---{V:-3}
  .   .   .   .   .   \---{Y:-98}
  ----{A:24}
  .   .   .   .   /---{A:9}
  .   .   .   /---{G:-1}
  .   .   .   .   .   /---{E2:89}
  .   .   .   .   \---{O:11}
  .   .   /---{D:-51}
  .   .   .   .   /---{T:92}
  .   .   .   .   .   \---{F2:-2}
  .   .   .   \---{E:-3}
  .   .   .   .   .   .   /---{W:-70}
  .   .   .   .   .   /---{S:30}
  .   .   .   .   \---{L:-58}
  .   \---{C:42}
  .   .   .   /---{R:-68}
  .   .   .   .   \---{C2:10}
  .   .   \---{H:-59}
  .   .   .   \---{P:-92}
  .   .   .   .   \---{B2:28}
  C2 => N: ( {C2:10} + {R:-68} + {H:-59} + {C:42} + {A:24} + {B:-9} + {M:37} + {N:44} ) = 21
  D2 => Y: ( {D2:15} + {Z:94} + {Q:-68} + {M:37} + {N:44} + {V:-3} + {Y:-98} ) = 21
  H => T: ( {H:-59} + {C:42} + {D:-51} + {E:-3} + {T:92} ) = 21
  J => N: ( {J:-95} + {F:44} + {B:-9} + {M:37} + {N:44} ) = 21
  N => C2: ( {N:44} + {M:37} + {B:-9} + {A:24} + {C:42} + {H:-59} + {R:-68} + {C2:10} ) = 21
  N => J: ( {N:44} + {M:37} + {B:-9} + {F:44} + {J:-95} ) = 21
  T => H: ( {T:92} + {E:-3} + {D:-51} + {C:42} + {H:-59} ) = 21
  Y => D2: ( {Y:-98} + {V:-3} + {N:44} + {M:37} + {Q:-68} + {Z:94} + {D2:15} ) = 21

