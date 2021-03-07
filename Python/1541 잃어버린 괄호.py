import sys
from collections import deque
input = sys.stdin.readline

expr = input().strip()
oper = deque()
num = deque()
number = ''
for i in expr:
    if i == '+' or i == '-':
        oper.append(i)
        num.append(int(number))
        number = ''
    else:
        number += i
num.append(int(number))

res = num.popleft()
flag = False
mid = 0
while(oper):
    cur = oper.popleft()
    cur_num = num.popleft()
    if(flag):
        if cur == '-':
            res -= mid
            mid = cur_num
        elif cur == '+':
            mid += cur_num
    else:
        if cur == '-':
            mid = cur_num
            flag = True
        elif cur == '+':
            res += cur_num

print(res - mid)