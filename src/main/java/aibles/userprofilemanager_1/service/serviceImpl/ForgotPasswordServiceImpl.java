package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.service.service.ForgotPasswordService;
import aibles.userprofilemanager_1.service.service.EmailService;
import aibles.userprofilemanager_1.repository.UserProfileRepository;
import aibles.userprofilemanager_1.entity.UserProfileEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final EmailService emailService;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public ForgotPasswordServiceImpl(EmailService emailService, UserProfileRepository userProfileRepository) {
        this.emailService = emailService;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void forgotPassword(String email) {

        UserProfileEntity userProfileEntity = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User profile not found for email: " + email));


        sendResetPasswordEmail(email);
    }


    private void sendResetPasswordEmail(String email) {

        emailService.sendResetPasswordEmail(email);
    }
}
