import os
import sys
sys.path.append(os.path.join(os.path.dirname(__file__),'../'))

from FiniteStateMachine.afd import AFD

class RegularGrammar:
    def __init__(self, terminals, non_terminals, productions, start_symbol):
        self.terminals = terminals
        self.non_terminals = non_terminals
        self.productions = productions
        self.start_symbol = start_symbol

    def display_components(self):
        result = "Gramatică regulată:\n"
        result += f"Terminale: {', '.join(self.terminals)}\n"
        result += f"Non-terminale: {', '.join(self.non_terminals)}\n"
        result += "Producții:\n"
        result += f"Simbol de start: {self.start_symbol}\n"
        for nt, rules in self.productions.items():
            for rule in rules:
                result += f"  {nt} -> {rule}\n"
        return result

    def is_regular(self):
        for nt, rules in self.productions.items():
            for rule in rules:
                if len(rule) > 2 or (len(rule) == 2 and (rule[0] not in self.terminals or rule[1] not in self.non_terminals)):
                    return False
                if len(rule) == 1 and rule not in self.terminals and rule != "":
                    return False
        return True

    def to_automaton(self):
        states = set(self.non_terminals)
        alphabet = set(self.terminals)
        start_state = self.start_symbol
        final_states = {"F"}
        transitions = {}

        for nt, rules in self.productions.items():
            for rule in rules:
                if len(rule) == 1 and rule in self.terminals:
                    if nt not in transitions:
                        transitions[nt] = {}
                    transitions[nt][rule] = "F"
                elif len(rule) == 2:
                    if nt not in transitions:
                        transitions[nt] = {}
                    transitions[nt][rule[0]] = rule[1]

        return AFD(states.union(final_states), alphabet, transitions, start_state, final_states)

    @staticmethod
    def from_file(file_path):
        with open(file_path, "r") as file:
            lines = file.readlines()

        non_terminals = lines[0].strip().split(",")
        terminals = lines[1].strip().split(",")
        start_symbol = lines[2].strip()
        productions = {}

        for line in lines[3:]:
            left, right = line.strip().split("->")
            if left not in productions:
                productions[left] = []
            productions[left].extend(right.split("|"))

        return RegularGrammar(terminals, non_terminals, productions, start_symbol)

# Exemplu de utilizare
if __name__ == "__main__":
    grammar_file = "regular_grammar.txt"

    # Exemplu de creare fișier de configurare pentru gramatică regulată
    # with open(grammar_file, "w") as file:
    #     file.write("S,A\n")  # Neterminale
    #     file.write("a,b\n")  # Terminale
    #     file.write("S\n")  # Simbol de start
    #     file.write("S->aA|b\n")  # Producții
    #     file.write("A->a|bS\n")

    grammar = RegularGrammar.from_file(grammar_file)
    print(grammar.display_components())
    
    print(f"Gramatica este regulată: {grammar.is_regular()}")

    automaton = grammar.to_automaton()
    print("Automatul rezultat din gramatică:")
    print(automaton.get_components())
