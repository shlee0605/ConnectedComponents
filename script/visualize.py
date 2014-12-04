import json
import re
import sys, getopt

"""

Json Format Converter for the visualization in D3.

Author: Seunghyun Lee (sl169@duke.edu)

"""

def main(argv):
    """
    main function takes care of the input arguments
    """
    if len(argv) < 2:
        print 'visualize.py -i <initial graph input data> -r <result file after k-th round> -o <outputfile>'
        exit(1)
    argv = argv[1:]

    inputfile = ''      # initial graph input data
    resultfile = ''     # intermediate result file after k-th round
    outputfile = ''     # output Json file
    try:
        opts, args = getopt.getopt(argv,"hi:o:r:",["ifile=","ofile=", "rfile="])
    except getopt.GetoptError:
        print 'visualize.py -i <initial graph input data> -r <result file after k-th round> -o <outputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'visualize.py -i <initial graph input data> -r <result file after k-th round> -o <outputfile>'
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-r", "--rfile"):
            resultfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg
    parse(inputfile, resultfile, outputfile)

def parse(inputfile, resultfile, outputfile):
    file = open(inputfile, 'r')
    result = {}
    nodes = []
    links = []

    # read in initial input graph and get the nodes, edges
    for line in file:
        data = line.split()
        print data[0]
        data_0 = int(data[0])
        data_1 = int(data[1])
        if data_0 not in nodes:
            nodes.append(data_0)
        if data_1 not in nodes:
            nodes.append(data_1)
        if(data[0] != data[1]):
            links.append(data)

    # convert the results into the json format
    nodes_result = []
    links_result = []

    if resultfile == '':
        for node in sorted(nodes):
            nodes_result.append({"name": str(node), "group": node})
    else:
        nodes_result = parse_round(resultfile)
    for link in links:
        links_result.append({"source": int(link[0]), "target":int(link[1]), "value" : 1})

    result["nodes"] = nodes_result
    result["links"] = links_result

    final = json.dumps(result)
    output = open(outputfile, 'w')
    output.write(final)

def parse_round(input):
    file = open(input, 'r')
    nodes=[]
    for line in file:
        data = re.split(r'[(,)]', line)
        nodes.append({"name": data[1], "group":data[3]})
    return nodes

if __name__ == '__main__':
    main(sys.argv)
