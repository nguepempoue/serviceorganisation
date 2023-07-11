package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.service.PostServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostBootstrap {

    @Autowired
    private PostServiceImplementation postServiceImplementation;

    public void seed() {

        if(postServiceImplementation.countAll() == 0L) {

            //CREATING DEFAULTS POSTS OF THE CLUB
            /*postServiceImplementation.createPost(new PostRequest("Pilote", 1, "description du poste", OrganisationLevelEnum.CLUB));*/

            //CREATING DEFAULTS POSTS OF TEH AREA
            /*postServiceImplementation.createPost(new PostRequest("Agent de communication", 1, "description du poste", OrganisationLevelEnum.AREA));
            postServiceImplementation.createPost(new PostRequest("Agent de saisie de données", 1, "description du poste", OrganisationLevelEnum.AREA));*/

            //CREATING DEFAULTS POSTS OF THE CENTER
/*
            postServiceImplementation.createPost(new PostRequest("Comptable", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Administrateur Système", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Assemblée générale des membres", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Comité de Développement", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Assemblée représentative des Clubs", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Conseil d’Administration", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Comité de Gouvernance et des Rémunérations", 1, "description du poste", OrganisationLevelEnum.CENTER));
            postServiceImplementation.createPost(new PostRequest("Responsable de Production", 1, "description du poste", OrganisationLevelEnum.CENTER));
*/
            //CREATING DEFAULTS POSTS OF THE MAIN OFFICE
/*
            postServiceImplementation.createPost(new PostRequest("Comité exécutif", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE));
            postServiceImplementation.createPost(new PostRequest("Assemblée générale", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE));
            postServiceImplementation.createPost(new PostRequest("Comité de gouvernance et de rémunération", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE));
            postServiceImplementation.createPost(new PostRequest("Comité de production et de surveillance", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE));
            postServiceImplementation.createPost(new PostRequest("Comité de développement stratégique", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE));
*/


        }
    }
}
