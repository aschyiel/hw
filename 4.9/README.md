Question 4.9:

You are given a binary tree in which each node contains a value.
Design an algorithm to print all paths which sum to a given value.
The path does not need to start or end at the root or a leaf.

My solution at a high-level:

Use DP, tally up each path, only print the exact values later.

medium-level details:
Use a matrix to memoize - A->B is the same answer as B->A as an optimization.
2nd optimization is to return early if we're already busted (ie. 21 in black-jack).
This only works if we assume that the values are all positive.

How is my solution different than the one in the book?

The book's answer allows for negative values.
The book's answer doesn't appear to allow going "across" the tree, ie. left->root->right.
In other words, it only calculates up to the tree's height, instead of 2 * tree-height.
The book's answer does NOT use DP, which is lame.

