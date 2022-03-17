///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS org.gitlab4j:gitlab4j-api:4.19.0
//DEPS org.slf4j:slf4j-simple:1.7.36
//REPOS id=https://bahnhub.tech.rz.db.de:443/artifactory/sab-maven-3rdparty

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

class gitlab {

    static final Logger logger = LoggerFactory.getLogger(gitlab.class);

    public static void main(String[] args) throws GitLabApiException {
        logger.info("Welcome to jbang");

        Arrays.asList(args).forEach(arg -> logger.warn("arg: " + arg));
        logger.info("Hello from Java!");

        // Create a GitLabApi instance to communicate with your GitLab server
        String gitlabToken = System.getenv("JBANG_GITLAB_TOKEN");

        GitLabApi gitLabApi = new GitLabApi("https://git.tech.rz.db.de/", gitlabToken);

        // Get the list of projects your account has access to
        ProjectApi projectApi = gitLabApi.getProjectApi();
        projectApi.getProjectsStream(new ProjectFilter().withMembership(true))
                .map(Project::getName).forEach(System.err::println);
    }
}
