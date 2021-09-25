# Management-System-19I-2008
<p>I have created a console base management system in java. An account management system is an application for maintaining a personal account in a bank.</p>
<h2>Features of the system</h2>
<ul>
  <li>An account management system needs to store information about Bank Accounts and Customers.</li>
  <li>This system supports two different types of accounts (Checking and Savings).</li>
  <li>All customers have a name, address, and phone number. Each customer has their unique account number and the bank will identify customers by only that account number.</li>
  <li>A customer may open both a savings account and a checking account. However, a customer must not have more than one savings account or checking account.</li>
  <li>All bank accounts must have the account number, balance, and date created.</li>
  <li>There are some features that are defined for all accounts, checkBalance(), printStatement(), makeDeposit(), transferAmount(), calculateZakat() and makeWithdrawal() but the withdrawal limit is different based on the account type. Savings account customers cannot withdraw more than their balance but the checking account holders can withdraw more than their account balance (up to a maximum of 5000 PKR).</li>
  <li>Checking accounts have additional functionality that may have transaction fees for deposits and withdrawals. The account holder will get only 2 free transactions per month. After that, the account holder must pay a transaction fee (10 Rs) for each additional transaction such as deposit and withdrawal. A record of all transaction fees should be calculated.</li>
  <li>Saving accounts have an additional attribute for interest rate and an operation for calculatelnterest(). The interest rate remains the same for all accounts and can be changed at runtime.</li>
</ul>

<h2>Class Diagram</h2>
<h2>Console Interface</h2>
<img src="https://github.com/Anas-hameed/Management-System-19I-2008/blob/master/images/1.PNG"/>
<img src="https://github.com/Anas-hameed/Management-System-19I-2008/blob/master/images/2.PNG"/>
