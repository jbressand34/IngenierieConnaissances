/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharispe.rc_demo;

import openllet.jena.PelletReasonerFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;

/**
 *
 * @author sharispe
 */
public class MainPelletTest {

    public static void performSPARQLQuery(Model model, String queryString) {

        System.out.println(queryString);
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            ResultSetFormatter.out(System.out, results, query);
        }

    }

    public static void main(String[] args) {

        String onto_file = System.getProperty("user.dir") + "/resources/projetIngenierieConnaissance.owl";
        String data_file = System.getProperty("user.dir") + "/resources/data.nt";

        Model model = RDFDataMgr.loadModel(onto_file);
        RDFDataMgr.read(model, data_file);

        //https://jena.apache.org/documentation/query/app_api.html
        String queryA = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#Chaussure> }";
        String queryB = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureTravail>  }";
        String queryC = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureTravailBureau>  }";
        String queryD = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureTravailDeplacement>  }";
        String queryE = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureEvenement>  }";
        String queryF = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureEvenementChic>  }";
        String queryG = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureEvenementDecontracte>  }";
        String queryH = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureSport>  }";
        String queryI = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureSportAvecTerrain>  }";
        String queryJ = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureSportMontagne>  }";
        String queryK = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureSportMer>  }";
        String queryL = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureVoyage>  }";
        String queryM = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureVoyagePaysFroids>  }";
        String queryN = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureVoyagePaysTemperes>  }";
        
        //String queryA = "SELECT ?materiau WHERE { ?materiau <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#MateriauSemelle> .}";

        // reasoner using Jena
        //System.out.println("-------------------------------------");
        //System.out.println("-               JENA                -");
        //System.out.println("-------------------------------------");
        //Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        //InfModel inf = ModelFactory.createInfModel(reasoner, model);

        //performSPARQLQuery(inf, queryA);
        //performSPARQLQuery(inf, queryB);
        //performSPARQLQuery(inf, queryC);
        //performSPARQLQuery(inf, queryD);
        //performSPARQLQuery(inf, queryE);

        // reasoning using Openllet
        System.out.println("-------------------------------------");
        System.out.println("-             OPENLLET              -");
        System.out.println("-------------------------------------");
        Reasoner reasonerPellet = PelletReasonerFactory.theInstance().create();
        InfModel infOpenllet = ModelFactory.createInfModel(reasonerPellet, model);

        performSPARQLQuery(infOpenllet, queryA);
        performSPARQLQuery(infOpenllet, queryB);
        performSPARQLQuery(infOpenllet, queryC);
        performSPARQLQuery(infOpenllet, queryD);
        performSPARQLQuery(infOpenllet, queryE);
        performSPARQLQuery(infOpenllet, queryF);
        performSPARQLQuery(infOpenllet, queryG);
        performSPARQLQuery(infOpenllet, queryH);
        performSPARQLQuery(infOpenllet, queryI);
        performSPARQLQuery(infOpenllet, queryJ);
        performSPARQLQuery(infOpenllet, queryK);
        performSPARQLQuery(infOpenllet, queryL);
        performSPARQLQuery(infOpenllet, queryM);
        performSPARQLQuery(infOpenllet, queryN);

    }

}
