from utils.utils import *

def TestInsertScore():
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 0, 1, 7) == [[1, 7, 3], [4, 5, 6]]
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 1, 2, 5) == [[1, 2, 3], [4, 5, 5]]
    assert InsertScoreForGivenParticipant([[1, 2, 3], [4, 5, 6]], 2, 2, 5) == "<---Eroare--->"

def TestEraseScoreForGivenParticipant():
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 1, 1) == [[1, 2, 3], [1, 0, 5]]
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 2, 1) == "<---Eroare--->"
    assert EraseScoreForGivenParticipant([[1, 2, 3], [1, 2, 5]], 1, 4) == [[1, 2, 3], [1, 2, 5]]

def TestEraseScoreForInterval():
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2) == [[1, 2, 3], [1, 2]]
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 4, 2) == "<---Eroare--->"
    assert EraseScoreForInterval([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 4) == "<---Eroare--->"

def TestSubstituteScore():
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2, 8) == [[1, 2, 3], [1, 2, 8], [1, 5, 6], [1, 2]]
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 4, 2, 5) == "<---Eroare--->"
    assert SubstituteScore([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 4, 2) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]

def TestGetMinimScoreForInterval():
    assert getMinimScoreForInterval([[], [1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1, 2) == 2
    assert getMinimScoreForInterval(([], [1, 2, 3, 4, 5], [1, 2, 3], [0, 0, 1]), 1, 3) == 0

def TestFilterMultipleOfGivenNumber():
    assert FilterMultipleOfGivenNumber([[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 2) == [[1, 2]]
    assert FilterMultipleOfGivenNumber([[], [10, 10, 10], [5, 5, 3], [1, 6, 7, 10], [1, 2, 3]], 3) == [[10, 10, 10], [5, 5, 3], [1, 2, 3]]

def TestFilterParticipantWithLowerScore():
    assert FilterParticipantWithLowerScore([[], [1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 1) == []
    assert FilterParticipantWithLowerScore([[], [1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]], 10) == [[1, 2, 3], [1, 2, 5], [1, 5, 6], [1, 2]]

def AllTests():
    TestInsertScore()
    TestEraseScoreForGivenParticipant()
    TestEraseScoreForInterval()
    TestSubstituteScore()
    TestGetMinimScoreForInterval()
    TestFilterMultipleOfGivenNumber()
    TestFilterParticipantWithLowerScore()
