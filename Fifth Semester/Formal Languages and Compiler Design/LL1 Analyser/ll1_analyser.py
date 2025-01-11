class LL1Parser:
    def __init__(self, grammar):
        self.grammar = grammar  # Grammar rules as a dictionary
        self.non_terminals = set(grammar.keys())
        self.terminals = self.get_terminals()
        self.first = {nt: set() for nt in self.non_terminals}
        self.follow = {nt: set() for nt in self.non_terminals}
        self.table = {}

    def get_terminals(self):
        terminals = set()
        for productions in self.grammar.values():
            for production in productions:
                for symbol in production:
                    if symbol not in self.non_terminals and symbol != 'epsilon':
                        terminals.add(symbol)
        return terminals

    def compute_first(self):
        for terminal in self.terminals:
            self.first[terminal] = {terminal}

        updated = True
        while updated:
            updated = False
            for non_terminal in self.non_terminals:
                for production in self.grammar[non_terminal]:
                    old_first = self.first[non_terminal].copy()

                    for symbol in production:
                        if symbol in self.first:  # Ensure symbol exists in first
                            self.first[non_terminal].update(self.first[symbol] - {'epsilon'})
                            if 'epsilon' not in self.first[symbol]:
                                break
                        else:
                            break
                    else:
                        self.first[non_terminal].add('epsilon')

                    if old_first != self.first[non_terminal]:
                        updated = True


    def compute_follow(self):
        for non_terminal in self.non_terminals:
            self.follow[non_terminal] = set()

        start_symbol = next(iter(self.grammar))
        self.follow[start_symbol].add('$')

        updated = True
        while updated:
            updated = False
            for non_terminal in self.non_terminals:
                for production in self.grammar[non_terminal]:
                    follow_set = self.follow[non_terminal].copy()
                    for symbol in reversed(production):
                        if symbol in self.non_terminals:
                            old_follow = self.follow[symbol].copy()
                            self.follow[symbol].update(follow_set)
                            if 'epsilon' in self.first.get(symbol, set()):
                                follow_set.update(self.first[symbol] - {'epsilon'})
                            else:
                                follow_set = self.first[symbol].copy()
                            if old_follow != self.follow[symbol]:
                                updated = True
                        elif symbol in self.terminals:
                            follow_set = {symbol}  # Directly set follow_set to the terminal


    def build_table(self):
        self.table = {}
        for non_terminal in self.non_terminals:
            for production in self.grammar[non_terminal]:
                first_set = set()
                for symbol in production:
                    if symbol in self.first:
                        first_set.update(self.first[symbol] - {'epsilon'})
                        if 'epsilon' not in self.first[symbol]:
                            break
                    elif symbol in self.terminals:  # Handle terminals directly
                        first_set.add(symbol)
                        break
                else:
                    first_set.add('epsilon')

                for terminal in first_set - {'epsilon'}:
                    if (non_terminal, terminal) in self.table:
                        raise ValueError(f"Conflict in LL(1) table at ({non_terminal}, {terminal})")
                    self.table[(non_terminal, terminal)] = production

                if 'epsilon' in first_set:
                    for terminal in self.follow[non_terminal]:
                        if (non_terminal, terminal) in self.table:
                            raise ValueError(f"Conflict in LL(1) table at ({non_terminal}, {terminal})")
                        self.table[(non_terminal, terminal)] = production

    def parse(self, input_string):
        stack = ['$']
        stack.append(next(iter(self.grammar)))  # Start symbol
        input_string += '$'
        pointer = 0

        while stack:
            top = stack.pop()
            if top == input_string[pointer]:
                pointer += 1
            elif top in self.terminals or top == '$':
                return False
            elif (top, input_string[pointer]) in self.table:
                production = self.table[(top, input_string[pointer])]
                if production != ['epsilon']:
                    stack.extend(reversed(production))
            else:
                return False

        return pointer == len(input_string)

print("------------------GRAMATICA CURS 8------------------")
grammar = {
    'S': [['B', 'e']],
    'B': [['epsilon'], ['B', '(', 'B', ')']]
}

parser = LL1Parser(grammar)
parser.compute_first()
parser.compute_follow()
parser.build_table()

print("FIRST sets:")
for non_terminal, first_set in parser.first.items():
    print(f"{non_terminal}: {first_set}")

print("\nFOLLOW sets:")
for non_terminal, follow_set in parser.follow.items():
    print(f"{non_terminal}: {follow_set}")

print("\nLL(1) Table:")
for key, value in parser.table.items():
    print(f"{key}: {value}")

# Parsing example
test_string = "(e)"
print(f"\nParsing '{test_string}':", parser.parse(test_string))

print("------------------GRAMATICA LAB 1------------------")
grammar = {
    'S': [['Letter', 'Rest']],
    'Rest': [['Letter', 'Rest'], ['Digit', 'Rest'], ['epsilon']],
    'Letter': list("abcdefghijklmnopqrstuvwxyz_"),
    'Digit': list("0123456789")
}


parser = LL1Parser(grammar)
parser.compute_first()
parser.compute_follow()
parser.build_table()

print("FIRST sets:")
for non_terminal, first_set in parser.first.items():
    print(f"{non_terminal}: {first_set}")

print("\nFOLLOW sets:")
for non_terminal, follow_set in parser.follow.items():
    print(f"{non_terminal}: {follow_set}")

print("\nLL(1) Table:")
for key, value in parser.table.items():
    print(f"{key}: {value}")

# Parsing example
test_string = "myvar_1"
print(f"\nParsing '{test_string}':", parser.parse(test_string))
