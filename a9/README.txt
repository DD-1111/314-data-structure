1.When a file is large or its characters have very uneven frequencies, it generally leads to more compressions. One reason is because the header and the standard count format by itself takes a lot of bits to write but it remains the same size no matter how big the file is, the standard tree format is much smaller but has a similar effect(does scale a little with the size of the file but would never have more 256 leafs). Also with larger file, we are more likely to see an uneven distribution of character frequency, which means we can save more space by changing the high frequency words to a shorter code. 
2.So small file or file with evenly distributed character frequencies has little to no compression. If all the characters in a file have the same frequency we have to assign the same length codes to all of them which means we can actually ended up with a larger file.
3.The compressed Huffman code is 36% smaller in size compared to the original file. Java programs uses certain characters a lot more than others, this lead to a fair compression.

SCF:

MostlyEs.txt: 

Original bits: 867896
Total compressed bits: 128800

allWAST

Original bits: 429120
Total compressed bits: 117700

STF:

MostlyEs.txt
Original bits: 867896
Total compressed bits: 122509

USA_2008:
Original bits: 14149248
Total compressed bits: 9804649
