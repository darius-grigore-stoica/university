class AFD:
    def __init__(self, states, alphabet, transitions, start_state, final_states):
        self.states = states
        self.alphabet = alphabet
        self.transitions = transitions
        self.start_state = start_state
        self.final_states = final_states

    def get_components(self):
        components = "Automatul are următoarele componente:\n"
        components += "Stări:\n  " + ", ".join(self.states) + "\n"
        components += "Alfabet:\n  " + ", ".join(self.alphabet) + "\n"
        components += "Starea inițială:\n  " + self.start_state + "\n"
        components += "Stări finale:\n  " + ", ".join(self.final_states) + "\n"
        components += "Tranziții:\n"
        for state, transitions in self.transitions.items():
            for symbol, next_state in transitions.items():
                components += f"  {state} --({symbol})--> {next_state}\n"
        return components


    def is_accepted(self, sequence):
        current_state = self.start_state
        print(f"Stare inițială: {current_state}")
        for symbol in sequence:
            print(f"Procesăm simbolul: {symbol}")
            if symbol not in self.alphabet:
                print(f"Simbolul {symbol} nu este în alfabet.")
                return False
            if current_state not in self.transitions or symbol not in self.transitions[current_state]:
                print(f"Nu există tranziție pentru starea {current_state} cu simbolul {symbol}.")
                return False
            current_state = self.transitions[current_state][symbol]
            print(f"Am trecut în starea: {current_state}")
        print(f"Stare finală: {current_state}")
        return current_state in self.final_states


    def next_state(self, current_state, symbol):
        if current_state in self.transitions and symbol in self.transitions[current_state]:
            return self.transitions[current_state][symbol]
        return None


def read_afd_from_file(file_path):
    with open(file_path, "r") as file:
        lines = file.readlines()

    states = set(lines[0].strip().split(","))
    alphabet = set(lines[1].strip().split(","))
    start_state = lines[2].strip()
    final_states = set(lines[3].strip().split(","))
    transitions = {}

    for line in lines[4:]:
        parts = line.strip().split(",")
        state, symbol, next_state = parts[0], parts[1], parts[2]
        if state not in transitions:
            transitions[state] = {}
        transitions[state][symbol] = next_state

    return AFD(states, alphabet, transitions, start_state, final_states)


def create_identifier_afd_config(file_path):
    with open(file_path, "w") as file:
        file.write("q0,q1\n")  # States
        file.write("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,_,0,1,2,3,4,5,6,7,8,9\n")  # Alphabet
        file.write("q0\n")  # Start state
        file.write("q1\n")  # Final states
        for ch in "abcdefghijklmnopqrstuvwxyz0123456789_":
            file.write(f"q0,{ch},q1\n") # Transitions
            file.write(f"q1,{ch},q1\n")

def create_numeric_constant_afd_config(file_path):
    with open(file_path, "w") as file:
        file.write("q0,q1\n")  # States
        file.write("0,1,2,3,4,5,6,7,8,9\n")  # Alphabet
        file.write("q0\n")  # Start state
        file.write("q1\n")  # Final states
        for ch in "0123456789":
            if ch != '0':
                file.write(f"q0,{ch},q1\n")
            file.write(f"q1,{ch},q1\n")

if __name__ == "__main__":
    identifier_file = "identifier_afd.txt"
    numeric_constant_file = "numeric_constant_afd.txt"

    create_identifier_afd_config(identifier_file)
    create_numeric_constant_afd_config(numeric_constant_file)

    afd = read_afd_from_file(identifier_file)
    afd_numerical = read_afd_from_file(numeric_constant_file)

    print("Automatul pentru identificatori:")
    print(afd.get_components())

    test_sequence = "my_var1"
    print(f"Secvența '{test_sequence}' este acceptată: {afd.is_accepted(test_sequence)}")

    test_sequence = "573410005"
    print(f"Secvența '{test_sequence}' este acceptată: {afd_numerical.is_accepted(test_sequence)}")

    current_state = "q1"
    symbol = "a"
    print(f"Starea următoare din '{current_state}' cu simbolul '{symbol}': {afd.next_state(current_state, symbol)}")
