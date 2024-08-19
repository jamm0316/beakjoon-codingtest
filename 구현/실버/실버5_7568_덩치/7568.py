def main():
return

class BigGuy:
    def __init__(self):
    self.N = 0
    self.people = []
    
    def parse_data(self):
    self.N = 5
    self.people = [[wieght, tall]]
    
    def count_bigger(self):
        rank = []
        for person in self.people:
            count = 0
            for i in range(self.people):
                if person[0] < self.people[i][0] and person[1] < self.people[i][1]
                    count += 1
            rank.append(count + 1)

        return rank

    def print_rank(self):
        rank = self.count_bigger()
        print(' '.join(map(str, rank)))
