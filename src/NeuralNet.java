
import org.neuroph.core.data.DataSet;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.ArrayList;


public class NeuralNet {

    // create new perceptron network
    NeuralNetwork neuralNetwork = new Perceptron(2, 1);

        public NeuralNet(){


// create training set
            DataSet trainingSet = new DataSet(2, 4);
// add training data to training set (logical OR function)
            trainingSet.add(new DataSetRow(new double[]{0, 0}, new double[]{0}));


// learn the training set
            neuralNetwork.learn(trainingSet);
// save the trained network into file
            neuralNetwork.save("neuralNetwork.save(\");\n");




// set network input
            neuralNetwork.setInput(1, 1);
// calculate network
            neuralNetwork.calculate();
// get network output
            double[] networkOutput = neuralNetwork.getOutput();
            System.out.println(networkOutput);


        }



}
