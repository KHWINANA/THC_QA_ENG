import ssl
import socket
from datetime import datetime

def get_certificate_expiry(hostname):
    context = ssl.create_default_context()
    with socket.create_connection((hostname, 443)) as sock:
        with context.wrap_socket(sock, server_hostname=hostname) as ssock:
            cert = ssock.getpeercert()
            expiry_date = datetime.strptime(cert['notAfter'], '%b %d %H:%M:%S %Y %Z')
            return expiry_date

def main():
    hostname = 'www.ultimateqa.com'
    try:
        expiry_date = get_certificate_expiry(hostname)
        print(f"The SSL certificate for {hostname} expires on: {expiry_date}")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    main()
