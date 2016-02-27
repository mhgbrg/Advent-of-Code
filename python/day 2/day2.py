def main():
    f = open('input.txt', 'r')
    amount_of_paper = 0
    amount_of_ribbon = 0
    for line in f:
        dimensions = line.rstrip('\n')
        sides = line.split('x')
        sides = map(lambda x : int(x), sides)
        amount_of_paper += get_amount_of_paper_needed_for_present(sides)

        sides.sort()
        amount_of_ribbon = amount_of_ribbon + 2 * sides[0] + 2 * sides[1] + reduce(lambda x, y: x * y, sides)

    print 'amount of paper', amount_of_paper
    print 'amount of ribbon', amount_of_ribbon

def get_amount_of_paper_needed_for_present(sides):
    areas = [sides[0] * sides[1], sides[1] * sides[2], sides[2] * sides[0]]
    return 2 * areas[0] + 2 * areas[1] + 2 * areas[2] + min(areas)

def get_amount_of_ribbon_needed_for_present(sides):
    return 2 * sides[0] + 2 * sides[1] + reduce(lambda x, y: x * y, sides)

if __name__ == '__main__':
    main()