# Perceptron Iris Binary Classifier

A simple **Perceptron** implementation in Java that classifies iris flowers into two species:
- *Iris Versicolor*
- *Iris Virginica*

The project uses a **binary subset** of the classic **Iris dataset**, excluding *Iris Setosa*.

---

## 🧠 How It Works

The perceptron is a **linear classifier** that learns to separate two classes using a weight vector and bias.  
During training, it updates weights based on misclassified examples using the **Perceptron Learning Rule**.

You can easily modify parameters such as:
- Learning rate  
- Number of epochs  
- Training and testing dataset files  

---

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/shehabeldin-mohamed/Perceptron-IrisBinary-Classifier.git
   cd Perceptron-IrisBinary-Classifier
2. **Move to src and compile the program**
   ```bash
   cd src
   javac Perceptron.java
   javac Main.java
3. **Run the program by specifying the learning rate, training dataset, and test dataset
For example:**
   ```bash
   java Main.java 0.01 perceptron.data perceptron.test.data
