%%
%1.1
clc
clear 

load('data_for_eignfaces.mat', 'employees_DB','eignfaces_blk')
tag = 0;
for i = 1:100
    disp(i);
 for j = i+1:100
 A = eignfaces_blk(:,:,i);
 B = eignfaces_blk(:,:,j);
 %convert matrixes into vectors
 V1 = A(:,1);
 V2 = B(:,1);
 for a = 2:300
     V1 = [V1;A(:,a)];
 end
 for b = 2:300
     V2 = [V2;B(:,b)];
 end
 %calculate inner product
 if round(dot(V1,V2))==0
     tag= tag+1;
 end

 end
 if tag ~= 0
    disp("Orthogonal");
 else
    disp("Not orthogonal");
end
end
%% 
%for task1(2)
clc
clear

load('data_for_eignfaces.mat', 'employees_DB','eignfaces_blk')
eignfaces_blk_norm = zeros(450,300,100);
for i=1:100
A=eignfaces_blk(:,:,i);
%convert into vector
V = A(:,1);
for j=2:100
    V = [V;A(:,j)];
%check if it is a unit vector
 if  dot(V,V) == 1
     eignfaces_blk_norm(:,:,i) =A;
 else
     %if not, normalize it
     B = normalize(reshape(A,[450,300]));
     eignfaces_blk_norm(:,:,i) = B;
 end
end
end

%%
%1.3
clc
clear

load('data_for_eignfaces.mat', 'employees_DB','eignfaces_blk')
eignfaces_blk_norm = zeros(450,300,100);
for i=1:100
A=eignfaces_blk(:,:,i);
%convert into vector
V = A(:,1);
for j=2:100
    V = [V;A(:,j)];
%check if it is a unit vector
 if  dot(V,V) == 1
     eignfaces_blk_norm(:,:,i) =A;
 else
     %if not, normalize it
     B = normalize(reshape(A,[450,300]));
     eignfaces_blk_norm(:,:,i) = B;
 end
end
end

%%
%2
clc
clear

load('data_for_eignfaces.mat','eignfaces_blk');
im=imread('find_id.jpg');

%function [weights_of_face] = get_face_weights(im, eigenfaces_blk)
 A = double(im);
 B = zeros(450,300);
 weights_of_face = zeros(1,100);
 %averaging the eigenfaces to obtain 
 % a consistent representation in all feature 
 % spaces, which is equivalent to the mean face
 for i=1:100
     B = B + eignfaces_blk(:,:,i);
 end
 Meanface = B/100;
 covariance_matrix = A-Meanface;
 for j=1:100
     eignface = eignfaces_blk(:,:,j);
     face_vector = [];
     covariance_vector = [];
     for k=1:300
     %transforming the picture and the eigenface matrix 
     % into vectors
     face_vector = [face_vector;eignface(:,k)];
     covariance_vector = [covariance_vector;covariance_matrix(:,k)];
     end     
     %taking out the projection discrepancies, aka the weights
     weights_of_face(1,j) = dot(face_vector,covariance_vector)+0.01;
  end
  save('weights_of_face',"weights_of_face")
 %stem(weights_of_face),title('weights of face')
%end


%%
%3
clc
clear

load('data_for_eignfaces.mat', 'employees_DB','eignfaces_blk')
eignfaces_blk_norm = zeros(450,300,100);
for i=1:100
A=eignfaces_blk(:,:,i);
%convert into vector
V = A(:,1);
for j=2:100
    V = [V;A(:,j)];
%check if it is a unit vector
 if  dot(V,V) == 1
     eignfaces_blk_norm(:,:,i) =A;
 else
     %if not, normalize it
     B = normalize(reshape(A,[450,300]));
     eignfaces_blk_norm(:,:,i) = B;
 end
end
end
