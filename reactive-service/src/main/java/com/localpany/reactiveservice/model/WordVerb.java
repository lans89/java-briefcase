package com.localpany.reactiveservice.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@ToString
@Entity
@Table(name = "wordverb", schema = "dbo", catalog = "innertest2")
public class WordVerb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Verb es obligatorio")
    private String verb;
    @Column(name = "past_form")
    @NotBlank(message = "Verb en past form es obligatorio")
    private String pastForm;
    @Column(name = "participle_form")
    @NotBlank(message = "Verb en participio es obligatorio")
    private String participleForm;
}
