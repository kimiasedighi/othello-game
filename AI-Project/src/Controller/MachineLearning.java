package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class sortByFitness implements Comparator<Individual> {
    public int compare(Individual a, Individual b) {
        return a.fitness - b.fitness;
    }
}

public class MachineLearning {

    Population population = new Population();
    int generationCount = 0;

    public static void main(String[] args) {
        Random rn = new Random();

        MachineLearning demo = new MachineLearning();

        int popSize = 5;

        //Initialize population
        demo.population.initializePopulation(popSize);

        while (demo.generationCount < 5) {
            demo.generationCount++;

            //Calculate fitness of each individual
            demo.population.calculateFitness();

            //Be tartibe fitness sort mikonad
            Collections.sort(demo.population.individuals, new sortByFitness());


            //Do selection for parent, 2 best
            int parentsSize = 2, childrenSize = 2;
            Individual[] parents = new Individual[parentsSize];
            Individual[] children = new Individual[childrenSize];

            for (int i = 0; i < parentsSize; i++) {
                parents[i] = (demo.population.individuals.get(popSize - 1 - i));
                children[i] = new Individual();
                for (int j = 0; j < demo.population.individuals.get(0).geneLength; j++) {
                    children[i].genes[j] = parents[i].genes[j];
                }
            }

            //Select a random crossover point
            for (int j = 0; j < childrenSize-1; j += 2) {
                int crossOverPoint = rn.nextInt(demo.population.individuals.get(0).geneLength);
                for (int i = 0; i < crossOverPoint; i++) {
                    int temp = children[j].genes[i];
                    children[j].genes[i] = children[j+1].genes[i];
                    children[j+1].genes[i] = temp;
                }
            }

            //Do mutation under a random probability for every children
            for (Individual child : children) {
                if (rn.nextInt() % 6 < 4) {
                    //Select a random mutation point
                    int mutationPoint = rn.nextInt(demo.population.individuals.get(0).geneLength);
                    child.genes[mutationPoint] = rn.nextInt(1000);
                }
            }

            //Children ha ra be jaye 2 parent ke kamtarin fitness ra darand gharar midahim
            for (int i = 0; i < childrenSize; i++) {
                demo.population.individuals.set(i, children[i]);
            }

        }

        demo.population.calculateFitness();
        Collections.sort(demo.population.individuals, new sortByFitness());


        // maghadire nahayi baraye vazn ha ra chap mikonad
        System.out.println(demo.population.individuals.get(popSize - 1).genes[0]);
        System.out.println(demo.population.individuals.get(popSize - 1).genes[1]);
        System.out.println(demo.population.individuals.get(popSize - 1).genes[2]);
        System.out.println(demo.population.individuals.get(popSize - 1).genes[3]);
        System.out.println(demo.population.individuals.get(popSize - 1).genes[4]);
        System.out.println(demo.population.individuals.get(popSize - 1).genes[5]);

    }
}

class Individual {

    int fitness = 0;
    int geneLength = 6;
    int[] genes = new int[geneLength];

    public Individual() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = rn.nextInt(1000);
        }
    }

    public Individual(
            int d_score, int p_score, int f_score, int c_score, int l_score, int m_socre) {
        genes[0] = d_score;
        genes[1] = p_score;
        genes[2] = f_score;
        genes[3] = c_score;
        genes[4] = l_score;
        genes[5] = m_socre;
    }
}

class Population {

    int popSize;
    ArrayList<Individual> individuals = new ArrayList<>();

    //Initialize population
    public void initializePopulation(int size) {
        popSize = size;
        for (int i = 0; i < popSize; i++) {
            individuals.add(new Individual());
        }
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (int i = 0; i < individuals.size(); i++) {
            for (int j = i + 1; j < individuals.size(); j++) {
                new AIwithAIcontroller(individuals.get(i), individuals.get(j));
            }
        }
    }

}
