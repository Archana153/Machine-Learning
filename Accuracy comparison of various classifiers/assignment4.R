args <- commandArgs(TRUE)
dataURL<-as.character(args[1])
header<-as.logical(args[2])
d<-read.csv(dataURL,header = header)
# create 10 samples
set.seed(123)
for(i in 1:10) {
  cat("Running sample ",i,"\n")
  sampleInstances<-sample(1:nrow(d),size = 0.9*nrow(d))
  trainingData<-d[sampleInstances,]
  testData<-d[-sampleInstances,]
  # which one is the class attribute
  Class<-d[,as.integer(args[3])]
  # now create all the classifiers and output accuracy values:
  
  
  # example of how to output
  # method="kNN" 
  # accuracy=0.9
  # cat("Method = ", method,", accuracy= ", accuracy,"\n")
  
}
