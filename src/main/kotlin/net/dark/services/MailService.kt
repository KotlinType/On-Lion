package net.dark.services

import net.dark.utils.EnvConfig
import org.apache.commons.mail.SimpleEmail

object MailService {
    fun sendActivationMain(to: String, link: String) {
        val email = SimpleEmail()
        email.hostName = EnvConfig.smtpHost
        email.setSmtpPort(EnvConfig.smtpPort)
        email.setSSLOnConnect(true)
        email.setAuthentication(EnvConfig.smtpUser, EnvConfig.smtpPassword)
        email.setFrom(EnvConfig.smtpUser)
        email.addTo(to)
        email.subject = "Активация аккаунта OnLion"
        email.setMsg(
            "Для активации аккаунта перейдите по ссылке: $link"
        )
            .send()
    }
}
