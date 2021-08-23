package br.org.utfpr.erikvalcezio.repository_spring_bot_atividade05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.utfpr.erikvalcezio.repository_spring_bot_atividade05.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
