# AWS
___

## Getting started with Amazon EKS

### How to install different CLI’s using brew ? 
 - brew install awscli

 - brew install eksctl

 - brew install kubernetes-cli

While creating EKS clusters we need these 3 command line tools to interact with AWS, n k8 clusters created using eksctl.
___

## What is EKS
- AWS EKS (Amazon Elastic Kubernetes Service) is a managed container orchestration service provided by Amazon Web Services (AWS). 
- It simplifies the deployment, management, and scaling of containerized applications using Kubernetes. 
- AWS EKS is suitable for organizations and developers looking to leverage Kubernetes for container orchestration without the complexity of managing the underlying infrastructure. 
- It offers a reliable, scalable, and secure environment for deploying and managing containerized applications in the AWS cloud.

___
## What is Amazon EKS control plane architecture?
- The control plane in a Kubernetes cluster is like the brain or central command center of the cluster. It is responsible for managing and controlling all the nodes (machines) and applications running within the cluster. 
- It runs on managed master nodes that are fully managed and maintained by AWS. 
- The control plane consists of several components that work together:
  - **API Server:** This is like the interface or entry point for users and other components to interact with the cluster. It receives requests and instructions and communicates with other components to carry out the necessary actions. 
  - **etcd**: This is like the memory or database of the cluster. It stores the current state and configuration information of the cluster, including things like which nodes are part of the cluster, which applications are running, and other important details.

___
## What is Amazon EKS control plane architecture?
- **Controllers**: These are like the managers or supervisors of the cluster. They constantly monitor the cluster's state, compare it with the desired state (as defined by users or configurations), and take actions to bring the cluster to the desired state. For example, if a node fails, the Node Controller will detect it and take action to replace or repair the node. 
- **Scheduler**: This component is responsible for deciding where and how to run new applications or workloads within the cluster. It considers factors like available resources, constraints, and requirements to make smart decisions about workload placement. 
- **Networking**: The control plane also handles networking aspects, ensuring that nodes can communicate with each other and that applications running on different nodes can reach each other.

___

## How does Amazon EKS work?

- **Create an Amazon EKS cluster** in the AWS Management Console or with the AWS CLI or one of the AWS SDKs. 
- **Launch managed or self-managed Amazon EC2 nodes**, or deploy your workloads to AWS Fargate. 
- When your cluster is ready, you can **configure** your favorite Kubernetes tools, such as **kubectl, to communicate with your cluste**r. 
- **Deploy and manage workloads on your Amazon EKS cluster the same way that you would with any other Kubernetes environment** ( like Rancher, ). You can also view information about your workloads using the AWS Management Console.

## Prerequisites for creating EKS cluster
- Before starting with EKS cluster creation, you must install and configure the following tools and resources that you need to create and manage an Amazon EKS cluster.
    - Kubectl
    - eksctl
    - Required IAM permissions with AWS CLI
___
## Connect to AWS using CLI

### Why do we need AWS CLI?
- The AWS Command Line Interface (CLI) is a powerful tool that allows users to interact with various Amazon Web Services (AWS) resources and services through a command-line interface. 

### Generate Access key in AWS IAM for local connect through CMD

- Sign in to the AWS Management Console using your root user credentials. 
- Open the AWS Management Console and navigate to the AWS Identity and Access Management (IAM) service. 
- Inside IAM click manage Access Keys 
- Click create access key and download .csv file

### Connect to AWS through CMD using CLI
- Open cmd and run command “`aws configure`” 
- Enter `AWS Access Key ID` and `AWS secret access key`

Now to confirm that we have successfully connected to my aws account we use another command: `aws sts get-caller-identity`
>manishkumar@Manishs-MacBook-Air ~ % aws sts get-caller-identity
```java
{
"UserId": "9yxxeq0xx947",
"Account": "98jk8adee947",
"Arn": "arn:aws:iam::9uwo8qor9947:root"
}
```
___
## What is EKSCTL?
- eksctl is a simple command line tool for creating and managing Kubernetes clusters on Amazon EKS.
- https://eksctl.io/. 
- We can create a basic cluster in minutes with just one command 
  - eksctl create cluster
- A cluster will be created with default parameters :
- exciting auto-generated name, e.g., fabulous-mushroom-1527688624
- two m5.large worker nodes
- use the official AWS EKS AMI
- us-west-2 region
- a dedicated VPC (check your quotas)
- But we might need some customization so either we can
  - Create a yaml
  - Give params in command line
- eksctl provides the fastest and easiest way to create a new cluster with nodes for Amazon EKS

## How to deploy EKS cluster?
- Deploy eks cluster using below command
> SYNTAX: eksctl create cluster --name `cluster name` --region `region name` --nodegroup-name `node name` --node-type t3.medium --nodes 1**
> - **Actual command**: eksctl create cluster --name `aws-eks-cluster` --region `eu-west-2` --nodegroup-name `eks-cluster-node` --node-type t3.medium --nodes 1
- **--name cluster-name**: Specifies the name of the EKS cluster you want to create. Replace "cluster-name" with your desired name for the cluster.

- **--region region-name**: Specifies the AWS region where you want to create the EKS cluster. Replace "region-name" with the desired AWS region, such as "us-west-2" for US West (Oregon).

- **--nodegroup-name node-name**: Specifies the name of the node group within the EKS cluster. Replace "node-name" with your desired name for the node group.

- **--node-type t3.medium**: Specifies the EC2 instance type for the worker nodes in the node group. In this case, it is set to "t3.medium". You can choose a different instance type according to your requirements.

- **--nodes 1**: Specifies the desired number of worker nodes in the node group. In this case, it is set to "1". You can adjust this number as needed.

- By running this command, eksctl will create an EKS cluster with the specified name, in the specified region, with a node group using t3.medium instances and a desired capacity of 1 node.

- When the eksctl create cluster command completes successfully, it generates the **kubeconfig** file with the appropriate configurations, including the cluster's endpoint, authentication details, and other necessary information. This kubeconfig file is then stored on your local machine in the default location.

## Tasks done by this simple command
> **Actual command**: eksctl create cluster --name `aws-eks-cluster` --region `eu-west-2` --nodegroup-name `eks-cluster-node` --node-type t3.medium --nodes 1

**Cluster Configuration:**

- **Node Group Creation**: eksctl provisions the specified node group(s) within the EKS cluster. This involves launching EC2 instances or Fargate pods as worker nodes that join the cluster. The command sets up the necessary configuration, such as instance types, instance profiles, and scaling options.

- **kubeconfig Update**: Once the cluster, control plane, and node groups are created, eksctl updates the kubeconfig file on your local machine. The kubeconfig file is configured with the necessary authentication details, cluster endpoint, and other configurations required to connect to the EKS cluster using tools like kubectl.

- **IAM Role Creation**: eksctl creates an IAM role for the EKS cluster's control plane. This role grants necessary permissions to manage the cluster and its resources.

- **VPC Creation**: If a VPC is not already available, eksctl creates a new Amazon Virtual Private Cloud (VPC) with the required subnets, routing, and security groups. This VPC will be used for the EKS cluster's networking.

- **Control Plane Provisioning**: Control plane is a master node. The command provisions the EKS control plane, which manages the cluster's resources, networking, and scaling. eksctl interacts with the Amazon EKS service to create and configure the control plane components.

- **Cluster Verification**: After the cluster creation process, eksctl performs verification checks to ensure the cluster is successfully provisioned and accessible. It confirms that the nodes are running and communicating with the control plane..
```java

// This creation take around 20 minutes for creation

manishkumar@Manishs-MacBook-Air ~ % eksctl create cluster --name aws-eks-cluster --region eu-west-2 --nodegroup-name eks-cluster-node --node-type t3.medium --nodes 1 
2024-09-29 10:23:29 [ℹ]  eksctl version 0.191.0-dev+c736924d6.2024-09-27T00:54:42Z
2024-09-29 10:23:29 [ℹ]  using region eu-west-2
2024-09-29 10:23:30 [ℹ]  setting availability zones to [eu-west-2c eu-west-2b eu-west-2a]
2024-09-29 10:23:30 [ℹ]  subnets for eu-west-2c - public:192.168.0.0/19 private:192.168.96.0/19
2024-09-29 10:23:30 [ℹ]  subnets for eu-west-2b - public:192.168.32.0/19 private:192.168.128.0/19
2024-09-29 10:23:30 [ℹ]  subnets for eu-west-2a - public:192.168.64.0/19 private:192.168.160.0/19
2024-09-29 10:23:30 [ℹ]  nodegroup "eks-cluster-node" will use "" [AmazonLinux2/1.30]
2024-09-29 10:23:30 [ℹ]  using Kubernetes version 1.30
2024-09-29 10:23:30 [ℹ]  creating EKS cluster "aws-eks-cluster" in "eu-west-2" region with managed nodes
2024-09-29 10:23:30 [ℹ]  will create 2 separate CloudFormation stacks for cluster itself and the initial managed nodegroup
2024-09-29 10:23:30 [ℹ]  if you encounter any issues, check CloudFormation console or try 'eksctl utils describe-stacks --region=eu-west-2 --cluster=aws-eks-cluster'
2024-09-29 10:23:30 [ℹ]  Kubernetes API endpoint access will use default of {publicAccess=true, privateAccess=false} for cluster "aws-eks-cluster" in "eu-west-2"
2024-09-29 10:23:30 [ℹ]  CloudWatch logging will not be enabled for cluster "aws-eks-cluster" in "eu-west-2"
2024-09-29 10:23:30 [ℹ]  you can enable it with 'eksctl utils update-cluster-logging --enable-types={SPECIFY-YOUR-LOG-TYPES-HERE (e.g. all)} --region=eu-west-2 --cluster=aws-eks-cluster'
2024-09-29 10:23:30 [ℹ]  default addons coredns, vpc-cni, kube-proxy were not specified, will install them as EKS addons
2024-09-29 10:23:30 [ℹ]  
2 sequential tasks: { create cluster control plane "aws-eks-cluster", 
    2 sequential sub-tasks: { 
        2 sequential sub-tasks: { 
            1 task: { create addons },
            wait for control plane to become ready,
        },
        create managed nodegroup "eks-cluster-node",
    } 
}
2024-09-29 10:23:30 [ℹ]  building cluster stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:23:32 [ℹ]  deploying stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:24:02 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:24:33 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:25:33 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:26:34 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:27:35 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:28:35 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:29:36 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:30:37 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-cluster"
2024-09-29 10:30:40 [ℹ]  creating addon
2024-09-29 10:30:41 [ℹ]  successfully created addon
2024-09-29 10:30:42 [!]  recommended policies were found for "vpc-cni" addon, but since OIDC is disabled on the cluster, eksctl cannot configure the requested permissions; the recommended way to provide IAM permissions for "vpc-cni" addon is via pod identity associations; after addon creation is completed, add all recommended policies to the config file, under `addon.PodIdentityAssociations`, and run `eksctl update addon`
2024-09-29 10:30:42 [ℹ]  creating addon
2024-09-29 10:30:42 [ℹ]  successfully created addon
2024-09-29 10:30:42 [ℹ]  creating addon
2024-09-29 10:30:43 [ℹ]  successfully created addon
2024-09-29 10:32:47 [ℹ]  building managed nodegroup stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:32:48 [ℹ]  deploying stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:32:48 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:33:19 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:33:59 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:34:36 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:35:49 [ℹ]  waiting for CloudFormation stack "eksctl-aws-eks-cluster-nodegroup-eks-cluster-node"
2024-09-29 10:35:49 [ℹ]  waiting for the control plane to become ready
2024-09-29 10:35:50 [✔]  saved kubeconfig as "/Users/manishkumar/.kube/config"
2024-09-29 10:35:50 [ℹ]  no tasks
2024-09-29 10:35:50 [✔]  all EKS cluster resources for "aws-eks-cluster" have been created
2024-09-29 10:35:50 [✔]  created 0 nodegroup(s) in cluster "aws-eks-cluster"
2024-09-29 10:35:50 [ℹ]  nodegroup "eks-cluster-node" has 1 node(s)
2024-09-29 10:35:50 [ℹ]  node "ip-192-168-76-1.eu-west-2.compute.internal" is ready
2024-09-29 10:35:50 [ℹ]  waiting for at least 1 node(s) to become ready in "eks-cluster-node"
2024-09-29 10:35:50 [ℹ]  nodegroup "eks-cluster-node" has 1 node(s)
2024-09-29 10:35:50 [ℹ]  node "ip-192-168-76-1.eu-west-2.compute.internal" is ready
2024-09-29 10:35:50 [✔]  created 1 managed nodegroup(s) in cluster "aws-eks-cluster"
2024-09-29 10:35:52 [ℹ]  kubectl command should work with "/Users/manishkumar/.kube/config", try 'kubectl get nodes'
2024-09-29 10:35:52 [✔]  EKS cluster "aws-eks-cluster" in "eu-west-2" region is ready

```

___
## What is Kubeconfig file?
- The **kubeconfig file** is necessary for authenticating and **accessing the EKS cluster**.

- By default, the **kubeconfig file** is updated with the necessary information to connect to the newly created EKS cluster, allowing you to use tools like kubectl to interact with the cluster from your local machine..

- After creation of eks-cluster we get the ./kube/config that contains all information of our cluster.
- To get all the node we use command `kubectl get nodes`
```java
manishkumar@Manishs-MacBook-Air ~ % kubectl get nodes                                                                                                                
NAME                                         STATUS   ROLES    AGE   VERSION
ip-192-168-76-1.eu-west-2.compute.internal   Ready    <none>   12m   v1.30.4-eks-a737599
manishkumar@Manishs-MacBook-Air ~ % 
```
___
## AWS RDS
### Why AWS RDS?
- To maintain the application state, we have databases (i.e DB are stateful, DB should not roll back to the previous state until unless not explicitly rolled back).
- We can Deploy Our DB also as a Deployment / POD 
- But what if POD crashes? 
- Your data is lost and that's the task of DB - to manage/save data 
- Thus always deploy your stateless applications using deployments in Kubernetes.
- So now u have following options 
  - Deployments with Persistent volumes(a common area where you will dump all) - but not recommended bcz deployments are usually only for stateless applications while DB's are stateful. 
  - Stateful set with PV - difficult to manage n create 
  - Best way is to completely segregate your database out of your deployment cluster.
  - So use AWS RDS ( i.e AWS managed service for relational DBS).
- AWS RDS (Amazon Relational Database Service) is a fully managed database service provided by Amazon Web Services (AWS). It enables you to **set up**, **operate**, and **scale** relational databases in the cloud easily.
- By leveraging AWS RDS, you can `quickly provision database instances`, **scale resources up or down as** needed, and easily manage your databases using the AWS Management Console, command-line interface (CLI), or APIs. This allows you to focus on building applications without worrying about the underlying infrastructure and maintenance of your databases. 
- With AWS RDS, we have the option to choose from various popular relational database engines including
  - MySQL 
  - PostgreSQL 
  - MariaDB 
  - Oracle 
  - Microsoft SQL Server

> We create AWS RDS DB in same region in which our cluster is present *so that our latency reduces our operational
cost, also reduces*.

### How to setup AWS RDS?
- **Sign in to the AWS Management Console** 
- **Navigate to the RDS service**: Once you're logged in, search for "RDS" in the AWS Management Console search bar or locate the "Database" category and click on "RDS."
- **Choose a database engine**: On the RDS dashboard, click on the "Create database" button. You'll be prompted to choose a database engine. AWS RDS supports various engines like MySQL, PostgreSQL, Oracle, SQL Server, etc. Select the engine that suits your requirements and click on it. 
- **Select the appropriate database edition**: Next, choose the edition and version of the database engine you want to use. The available options will vary depending on the database engine you selected. 
- **Specify database details**: Provide the necessary information for your RDS instance, such as the DB instance identifier, username, password, and database name. You can also choose the instance size, allocated storage, and other configuration options according to your needs.
- **Configure additional settings**: Set up the remaining configuration options, such as the Virtual Private Cloud (VPC) and security groups for network access, database port number, backup settings, maintenance preferences, etc. Adjust these settings based on your requirements. 
- **Review and launch**: Once you've configured all the necessary settings, review your selections and make sure everything is accurate. Double-check the cost implications and ensure you understand the pricing associated with your RDS instance. When you're ready, click on the "Create database" or "Launch database" button. 
- **Wait for the creation process**: The RDS instance creation process may take a few minutes. You can monitor the progress on the RDS dashboard or check the status notifications. 
- **Access and manage your RDS instance**: Once the RDS instance is created successfully, you can access it using various methods like connecting through an application, using a database management tool, or connecting via the AWS Command Line Interface (CLI). The specific steps for accessing and managing your RDS instance will depend on the database engine you chose.
- The need for an EC2 connection would typically arise if you have specific networking requirements, such as establishing a private, secure connection between your EC2 instances and the RDS database instance without exposing it to the public internet. In such cases, you would configure the necessary networking settings, security groups, and subnets to establish the EC2-to-RDS connection.


