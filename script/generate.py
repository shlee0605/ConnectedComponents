import sys, getopt


"""

Graph Generator for Hash-To-Min algorithm test

Author: Seunghyun Lee (sl169@duke.edu)

"""

def main(argv):
    """
    main function takes care of the input arguments
    """    
    if len(argv) < 2:
        print 'generate.py -k <k-value> -o <outputfile>'
        exit(1)
    argv = argv[1:]
    k = 0
    outputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hk:o:",["k-value=","ofile="])
    except getopt.GetoptError:
        print 'generate.py -k <k-value> -o <outputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'generate.py -k <k-value> -o <outputfile>'
            sys.exit()
        elif opt in ("-k", "--kval"):
            k = int(arg)
        elif opt in ("-o", "--ofile"):
            outputfile = arg
    generate(k, outputfile)
    
def generate(k, output):
    """
    Graph generation algorithm
    """
    # open the output file.
    output = open(output, 'w')
    
    # initialize the graph
    graph=[]
    low = 0
    high = k * (k + 1) /2 - 1
    print low, high
    for i in range(k):
        graph.append([])
            
    # generate graph
    for i in range(k):
        for j in reversed(range(i, k)):
            if i % 2 == 0:
                graph[j].append(low)
                low = low + 1
            else:
                graph[j].append(high)
                high = high - 1
                
    # connect the head
    high = k * (k + 1) /2
    for i in range(k):
        graph[i].append(high)

    # write out the graph
    for i in range(len(graph)):
        for j in range(0, len(graph[i])-1):
            output.write(str(graph[i][j]) + '\t' + str(graph[i][j+1]) +'\n')
    output.close()   
    print 'node: ', (k * (k + 1) / 2  + 1)
    print 'edge: ', (k * (k + 1) / 2)
    print 'diameter: ', k
  
if __name__ == '__main__':
    main(sys.argv)
