def main():
    f = open('input.txt', 'r')
    instructions = f.read()
    go_up = instructions.count('(')
    go_down = instructions.count(')')
    floor = go_up - go_down
    print 'went to floor', floor

    on_floor = 0
    index = 1
    for instruction in instructions:
        if instruction == '(':
            on_floor = on_floor + 1
        elif instruction == ')':
            on_floor = on_floor - 1

        if on_floor == -1:
            break

        index = index + 1

    print 'went to basement on instruction', index

if __name__ == '__main__':
    main()