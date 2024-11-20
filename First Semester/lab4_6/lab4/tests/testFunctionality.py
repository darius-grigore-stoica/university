from functionality.functionality import *

def Test1():
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 0, 1, 7) == [[1, 7, 3], [4, 5, 6]]
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 1, 2, 5) == [[1, 2, 3], [4, 5, 5]]
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 2, 2, 5) == [[1, 2, 3], [4, 5, 6], [0, 0, 5]]

def Test2():
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 1, 1) == [[1, 2, 3], [1, 0, 5]]
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 2, 1) == [[1, 2, 3], [1, 2, 5]]
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 1, 4) == [[1, 2, 3], [1, 2, 5]]

def Test3():
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2) == [[1, 2, 3], [1, 2]]
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 4, 2) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 4) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]

def Test4():
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2, 8) == [[1, 2, 3], [1, 2, 8], [1, 5, 6], [1, 2]]
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 4, 2, 5) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 4, 2) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]

def Test5():
    assert getMinimScoreForInterval([[], [1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2) == 2


def AllTests():
    Test1()
    Test2()
    Test3()
    Test4()
    Test5()

#AllTests()
