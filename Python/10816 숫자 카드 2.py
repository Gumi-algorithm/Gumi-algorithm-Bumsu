import sys
input = sys.stdin.readline
print = sys.stdout.write
n = int(input())
num = list(map(int, input().split()))
dic = {}
for i in range(n):
    if(dic.get(num[i])):
        dic[num[i]] += 1
    else:
        dic[num[i]] = 1
n = int(input())
card = list(map(int, input().split()))
for i in card:
    print("0 " if dic.get(i) == None else str(dic.get(i)) + " ")