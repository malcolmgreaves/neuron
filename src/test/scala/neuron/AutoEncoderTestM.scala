package neuron
import breeze.stats.distributions._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import neuron.math._
import neuron.autoencoder._

@RunWith(classOf[JUnitRunner])
class AutoEncoderTestM extends FunSuite with Optimizable {
	test("Test auto-encoder using NeuronMatrix") {
	  val inputDimension = 20
	  val hiddenDimension = 10
	  nn = new SimpleAutoEncoder(0.1, 0.1)(inputDimension,hiddenDimension)().create()
	  //nn = new SparseLinearAE(0.0,1.0,1.0)(inputDimension,hiddenDimension)().create()
	  //nn = new SparseLinearAE(1.0,1.0,1.0)(inputDimension,hiddenDimension)().create() // Gradient check succeed
	  
	  val numOfSamples = 100
	  val xDataM = new NeuronMatrix(nn.inputDimension, numOfSamples, new Uniform(-1,1))
	  val yDataM = xDataM
	  
	  val w = gradCheckM(xDataM, yDataM, 1E-6)
	      
	  val time = System.currentTimeMillis();
	  val (obj3, w2) = trainx(xDataM, yDataM, w)
	  println(System.currentTimeMillis() - time, obj3)
	  //println(w.data)
	  //println(w2.data)
	  

	}
}
