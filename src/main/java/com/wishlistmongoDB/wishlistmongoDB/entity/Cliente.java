package com.wishlistmongoDB.wishlistmongoDB.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Cliente {


        @Id
        private String id;
        private String cpf;
        private String nome;

        //Getter and Setter

        public String getID() {
                return id;
        }

        public void setID(String id) {
                this.id = id;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

