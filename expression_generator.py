import random

open_parentheses = 0
operations = ['+', '-', '*'] # TODO: add checks for divide by 0
is_prev_num = False

for i in range(int(input())):
	if (open_parentheses > 0 and random.random() < 0.7):
		print(str(random.randint(1,9)) + ') ' + operations[random.randint(0,len(operations)-1)], end=' ')
		open_parentheses = open_parentheses - 1
		is_prev_num = False
	else:
		if (random.random() < 0.3):
			print('(', end='')
			open_parentheses = open_parentheses + 1
		print(str(random.randint(1,9)) + ' ' + operations[random.randint(0,len(operations)-1)], end=' ')
		is_prev_num = True

	if (i % 15 == 0 and i != 0):
		print('\\')

print(str(random.randint(1,9)), end='')

while (open_parentheses > 0):
	print(')', end='')
	open_parentheses = open_parentheses - 1

print('\n')