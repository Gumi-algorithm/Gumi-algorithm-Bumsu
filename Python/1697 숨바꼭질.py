N, K = map(int, input().split())

if N >= K:
    print(N-K)
else:
    check = [0 for i in range(K+2)]

    move_list = [N]
    check[N] = 1
    while move_list:
        move = move_list.pop(0)
        if move == K:
            print(check[K] - 1)
            break
        if move + 1 <= K+1 and check[move+1] == 0:
            check[move+1] = check[move] + 1
            move_list.append(move+1)
        if move - 1 >= 0 and check[move-1] == 0:
            check[move-1] = check[move] + 1
            move_list.append(move-1)
        if move*2 <= K+1 and check[move*2] == 0:
            check[move*2] = check[move] + 1
            move_list.append(move*2)