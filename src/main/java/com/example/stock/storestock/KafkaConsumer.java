package com.example.stock.storestock;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class KafkaConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final StockService stockService;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    // Injection du service de stock et du ObjectMapper dans le consumer
    public KafkaConsumer(StockService stockService, ObjectMapper objectMapper) {
        this.stockService = stockService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "commande-topic", groupId = "stock-group")
    public void consume(ConsumerRecord<String, String> record) {
        LOGGER.info("Consumed message: {}", record.value());

        try {
            // Désérialiser le message JSON en un objet JsonNode
            JsonNode articleNode = objectMapper.readTree(record.value());

            // Extraire le nom et la quantité de l'article depuis le JSON
            String nomArticle = articleNode.get("nomArticle").asText();
            int quantite = articleNode.get("qte").asInt();

            // Mise à jour du stock avec les données de l'article consommé
            stockService.updateStock(nomArticle, quantite);

        } catch (Exception e) {
            LOGGER.error("Erreur lors de la consommation du message Kafka", e);
        }
    }
}
