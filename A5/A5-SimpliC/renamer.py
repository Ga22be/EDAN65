import os
import sys

path = sys.argv[1]

for filename in os.listdir(path):
	if filename.endswith(".out"):
		newFilename = path + filename[:-4] + ".expected"
		os.rename(path + filename, newFilename)
		print newFilename