package com.example.intermove.Configurations;

import com.example.intermove.Entities.User.roletype;
import com.example.intermove.Security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/register").permitAll()




                .antMatchers("/user/add-user").hasAuthority(roletype.ADMIN.name())
                .antMatchers("/user//retrieve-all-usersr").hasAuthority(roletype.ADMIN.name())
                .antMatchers("/user//retrieve-user/{user-id}").hasAuthority(roletype.ADMIN.name())
                .antMatchers("/user//remove-user/{user-id}").hasAuthority(roletype.ADMIN.name())
                .antMatchers("/user/updateUser").hasAuthority(roletype.ADMIN.name())
                .antMatchers("/user/UpdatePassword/{oldPassword}/{newPassword}").hasAnyAuthority(roletype.ADMIN.name()
                        ,roletype.USER.name(),roletype.UNIVERSITY.name(), roletype.STUDENT.name(),roletype.ACCOMODATION_AGENT.name(),roletype.FORMER.name())
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/location").permitAll()
                .antMatchers("/Events/videochat2").permitAll()
                .antMatchers("/Claim/room").permitAll()

                .antMatchers("/Events/add").permitAll()
                .antMatchers("/Events/list").permitAll()
                .antMatchers("/Events/updateEvents/{{id}}").permitAll()
                .antMatchers("/Events/delete/{{id}}").permitAll()
                .antMatchers("/Events/getEventsbyid/{{id}}").permitAll()
                .antMatchers("/Events/getEventsbyTitle/{{title}}").permitAll()
                .antMatchers("/Events/affecter-user-event/{{id}}/{{idE}}").permitAll()
                .antMatchers("/Events/remove-user-event/{{id}}/{{idE}}").permitAll()
                .antMatchers("/Events").permitAll()
                .antMatchers("/Events/{{id}}").permitAll()
                .antMatchers("/Events/events/{{eventId}}/share/facebook").permitAll()
                .antMatchers("/Events/export").permitAll()
                .antMatchers("/Events/video-feed").permitAll()
                .antMatchers("/Claim/reclamation/{{id}}").permitAll()
                .antMatchers("/Claim/updateComplaintAdmin/{{id}}").permitAll()
                .antMatchers("/Claim/updateComplaint/{{id}}").permitAll()
                .antMatchers("/Claim/list").permitAll()
                .antMatchers("/Claim/delete/{{id}}").permitAll()
                .antMatchers("/Claim/getClaimbyid/{{id}}").permitAll()
                .antMatchers("/Claim/getStatus/{{status}}").permitAll()
                .antMatchers("/Claim/mostcomplainer").permitAll()
                .antMatchers("/Claim/duplicatecomplainers").permitAll()
                .antMatchers("/Claim/{{id}}/send-email").permitAll()
                .antMatchers("/twilio/make-call/{{userId}}").permitAll()
                .antMatchers("/candidacy/create").permitAll()
                .antMatchers("/candidacy/status/{{status}}").permitAll()
                .antMatchers("/candidacy/user/{{userId}}").permitAll()
                .antMatchers("/course/create").permitAll()
                .antMatchers("/tag/create").permitAll()
                .antMatchers("/tag/offers/{{offerId}}/tags").permitAll()

                //achraf
                .antMatchers("/quiz/addQuiz").permitAll()
                .antMatchers("/quiz/addQuestionToQuiz/{{idQuiz}}").permitAll()
                .antMatchers("/quiz/addResponseToQuestion/{questionId}/{studentId}").permitAll()
                .antMatchers("/quiz/diplayShuffledQuestionOfQuiz/{idQuiz}").permitAll()
                .antMatchers("/quiz/displayUsersByScore").permitAll()
                .antMatchers("/quiz/displayQuiz").permitAll()
                .antMatchers("/quiz/displayQuestion").permitAll()
                .antMatchers("/quiz/displayResponse").permitAll()
                .antMatchers("/quiz/deleteQuiz/{{idQuiz}}").permitAll()
                .antMatchers("/quiz/updateQuiz").permitAll()
                .antMatchers("/quiz/deleteQuestion/{{idQuestion}}").permitAll()
                .antMatchers("/quiz/updateQuestion").permitAll()
                .antMatchers("/quiz/getMostRespondedQuestionOnQuiz/{{idQuiz}}").permitAll()


                //skills
                .antMatchers("/gestionSkills/addSkillsToUser/{{id}}").permitAll()
                .antMatchers("/gestionSkills/retrieveByDomain/{{domain}}").permitAll()
                .antMatchers("/gestionSkills/retrieve-all-Skills").permitAll()
                .antMatchers("/gestionSkills/addSkillsToUser/{{id}}").permitAll()
                .antMatchers("/gestionSkills/updateSkills/{{id}}").permitAll()


//rayen
                .antMatchers("/Houses/AllHouses").permitAll()
                .antMatchers("/Houses/addHouse/{idAgency}").permitAll()
                .antMatchers("/Houses/updateHouse").permitAll()
                .antMatchers("/Houses/deleteHouse/{id}").permitAll()
                .antMatchers("/Houses/getHouseById/{id}").permitAll()
                .antMatchers("/Houses/SearchHouse").permitAll()
                .antMatchers("/Houses/upload/image").permitAll()
                .antMatchers("/Houses/get/image/{name}").permitAll()
                .antMatchers("/Houses/get/houseimage/{idh}").permitAll()
                .antMatchers("/checkout").permitAll()
                .antMatchers("/charge").permitAll()
                .antMatchers("/Agents/AllAgents").permitAll()
                .antMatchers("/Agents/addAgent/{idAgency}").permitAll()
                .antMatchers("/Agents/updateAgent").permitAll()
                .antMatchers("/Agents/deleteAgent/{id}").permitAll()
                .antMatchers("/Agents/getAgentById/{id}").permitAll()
                .antMatchers("/Agencies/AllAgencies").permitAll()
                .antMatchers("/Agencies/addAgency").permitAll()
                .antMatchers("/Agencies/updateAgency").permitAll()
                .antMatchers("/Agencies/deleteAgency/{id}").permitAll()
                .antMatchers("/Agencies/getAgencyById/{id}").permitAll()









                .antMatchers("/gestionSkills/pdf/generate/{{id}}").permitAll()




//koch


                .antMatchers("/gestionoffre/addoffer").permitAll()
                .antMatchers("/gestionoffre/retrieve-all-offres").permitAll()
                .antMatchers("/gestionoffre/updateOffre1/{{idoffre}}").permitAll()
                .antMatchers("/gestionoffre/retrieve-offre/{{idoffre}}").permitAll()
                .antMatchers("/gestionoffre/remove-offre/{{idoffre}}").permitAll()
                .antMatchers("/gestionoffre/remove-offre/{{idoffre}}/{{id}}").permitAll()
                .antMatchers("/gestionoffre/findByHistorique1").permitAll()
                .antMatchers("/gestionoffre/getHistorique/{{historique}}").permitAll()
                .antMatchers("/gestionoffre/getnboffresexist/{{startDate}}/{{endDate}}").permitAll()
                .antMatchers("/gestionoffre/getoffresdto").permitAll()
                .antMatchers("/gestionoffre/findByHistorique1dto").permitAll()
                .antMatchers("/gestionoffre/export/{{id}}").permitAll()
                .antMatchers("/gestionoffre/offers/interesse").permitAll()
                .antMatchers("/gestionoffre/getdomain/{{domain}}").permitAll()
                .antMatchers("/gestionoffre/getoffresexistdto/{{startDate}}/{{endDate}}").permitAll()
                .antMatchers("/history/history/{{idoffre}}").permitAll()
                .antMatchers("/history/history2/{{idoffre}}").permitAll()
                .antMatchers("/interessant/interesse/{{idoffre}}").permitAll()
                .antMatchers("/interessant/remove-offre/{{id}}").permitAll()
                .antMatchers("/interessant").permitAll()


//farouk

                .antMatchers("/messages").permitAll()
                .antMatchers("/posts").permitAll()
                .antMatchers("/posts/{postId}").permitAll()
                .antMatchers("/comments").permitAll()
                .antMatchers("/comments/{commentId}").permitAll()
                .antMatchers("/messages/test/{userid}").permitAll()
                .antMatchers("/messages/conversation").permitAll()

















                .antMatchers("/candidacy/{{id}}").permitAll()
                .antMatchers("InterMove/candidacy/all").permitAll()
                .antMatchers("InterMove/candidacy/create").permitAll()
                .antMatchers("InterMove/convert").permitAll()
                .antMatchers("/convert").permitAll()
                .antMatchers("/email/send").permitAll()
                .antMatchers("/candidacy/ctag/{{id}}").permitAll()
                .antMatchers("/candidacy/updatestatus/{{id}}/{{score}}").permitAll()
                .antMatchers("/candidacy/update/{{id}}").permitAll()
                .antMatchers("/candidacy/user/{{userId}}").permitAll()
                .antMatchers("/email/send/{id}").permitAll()
                .antMatchers("/candidacy/updatesituationaccepted/{{id}}").permitAll()
                .antMatchers("/candidacy/updatesituationrejected/{id}").permitAll()
                .antMatchers("/candidacy/update/{{id}}").permitAll()
                .antMatchers("/candidacy/status/{{status}}").permitAll()
                .antMatchers("/candidacy/user/{{userId}}").permitAll()
                .antMatchers("/course/create").permitAll()

























                .antMatchers("/chat/**").permitAll() // New rule added to permit access to /chat endpoint
                .anyRequest().authenticated()
                .and()
                //.oauth2Login().and().oauth2Client().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

}
