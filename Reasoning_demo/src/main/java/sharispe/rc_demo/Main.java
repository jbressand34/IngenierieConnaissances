/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharispe.rc_demo;

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
public class Main {

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
        String queryB = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#Chaussure> }";
        //String queryA = "SELECT ?chaussure WHERE { ?chaussure <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#ChaussureTravail>  }";
        String queryA = "SELECT ?materiau WHERE { ?materiau <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.choisirseschaussures.fr/ontology#MateriauSemelle> .}";
        
        
        performSPARQLQuery(model, queryA);
        performSPARQLQuery(model, queryB);

        // reasoner 
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        InfModel inf = ModelFactory.createInfModel(reasoner, model);

        performSPARQLQuery(inf, queryA);

    }

}
