%%
%1
clc
clear

A = imread('lenna512_low_dynamic_range.bmp');
%[count1,x] = imhist(A,256);
%B = stem(x,count1); 
C = uint8(histeq(A,256));
%imwrite(C,'lenna512_ldr_euqal.bmp')
%[count2,y] = imhist(C,256);
%D = stem(y,count2);
imhist(C,256);

%%
%2
%mian
%% for task2q1
clc
clear
im = imread('lenna512.bmp');
result = task2q1(im);
%% for task2q2
clc
clear
% Paramters to pass into function
im = imread('lenna512.bmp');
QP = N;
Qmat=[16 11 10 16 24 40 51 61;
 12 12 14 19 26 58 60 55;
 14 13 16 24 40 57 69 56;
 14 17 22 29 51 87 80 62;
 18 22 37 56 68 109 103 77;
 24 35 55 64 81 104 113 92;
 49 64 78 87 103 121 120 101;
 72 92 95 98 112 100 103 99];
result = task2q2(im, Qmat, QP);
%% for task2q3
clc
clear
load('quantized_blocks');
im = imread('lenna512.bmp');
QP = N;
Qmat=[16 11 10 16 24 40 51 61;
 12 12 14 19 26 58 60 55;
 14 13 16 24 40 57 69 56;
 14 17 22 29 51 87 80 62;
 18 22 37 56 68 109 103 77;
 24 35 55 64 81 104 113 92;
 49 64 78 87 103 121 120 101;
 72 92 95 98 112 100 103 99];
result = task2q2(im, Qmat, QP, quantized_blocks);

%% be sure to run this section before running each function
clc
clear
%% this is for task2q1
function [ims] = task2q1(im)
A  = double(im);
B = zeros(64,64);
for i = 1:64
    for j = 1:64
        %Split the original image into non-overlapping 8*8blocks
        %making a total of 64*64=4096
        C = A((8*i-7):8*i, (8*j-7):8*j);
        %Call the build-in function to perform a 
        %2-dimentional Fourier cosine on each block
        D = dct2(double(C));
        %Fill the top left element of the 
        %completed block to a new matrix
        B(i,j) = D(1,1);
    end
end
ims = imshow(B,[],'border','tight','initialmagnification','fit');
%imwrite(ims,[],'ims.bmp');
%figure,imagesc(B);
end
%% this is for task2q2
function [quantized_blocks] = task2q2(im, Qmat, QP)
A  = double(im);
quantized_blocks = {};
%On request sq
if QP>=100 || QP<=0 
    error('QP should be in range from 0 to 100');
elseif QP>50
    S = (100-QP) / 50;
else
    S = 50/QP;
end
sq = S * Qmat;

for i = 1:64
    for j = 1:64
        %Split the original image into non-overlapping 8*8blocks
        %making a total of 64*64=4096
        C = A((8*i-7):8*i, (8*j-7):8*j);
        %Call the build-in function to perform a 
        %2-dimentional Fourier cosine on each block
        D = dct2(double(C));
        %use the formula to quantilize each block
        quantized_blocks{end+1} = round(D./sq);
    end
end
save('quantized_blocks', 'quantized_blocks');
end
%% this is for task2q3
function [imo] = task2q3(quantized_blocks, Qmat, QP)
imo  = zeros(512,512);
if QP>=100 || QP<=0 
    error('QP should be in range from 0 to 100');
elseif QP>50
    S = (100-QP) / 50;
else
    S = 50/QP;
end
sq = S * Qmat;

    for k = 1:4096
        %reverse quatization
        A = cell2mat(quantized_blocks(1,k))*sq;
        %reverse dct2
        B = idct2(A);
        for i = 64
            for j =64
        imo((8*i-7):8*i, (8*j-7):8*j) = B;
            end
        end
    end

imshow(uin8(imo));
end
%% this is for task2q4
function imo = task2q4(ims,Qmat,QP)
    if QP>=100 || QP<=0 
        error('QP should be in range from 0 to 100');
    elseif QP>50
        S = (100-QP) / 50;
    else
        S = 50/QP;
    end
    de_quan = blockproc(ims,[8 8],@(block_struct)block_struct.data.*(S*Qmat));
    imo = cast(blockproc(de_quan,[8 8],@(block_struct)idct2(block_struct.data)),'uint8');
end

%%
%3
%mian
%% for entropy
clc
clear
%im = imread('lenna512.bmp');
%im = imread('p1.bmp');
%im = imread('p2.bmp');
%im = imread('p3.bmp');
im = imread('p1.bmp');
result = entropy(im);
%% this is for entropy
function [H_x1] = entropy(im)
[r,c] = size(im);
% The total number of pixels in an image
img_size = r*c;
% The grayscale of the image
G = 256; 
H_x1 = 0;
nk=zeros(G,1);
for i = 1:r
    for j = 1:c
        img_level = im(i,j)+1; % Get the gray level of the image
        nk(img_level) = nk(img_level)+1; % Count the points of each gray level pixel
    end
end
for k = 1:G  
    Ps(k) = nk(k)/img_size; % Calculate the probability of each pixel
    if Ps(k)~=0
        H_x1 = -Ps(k)*log2(Ps(k))+ H_x1; % The formula for entropy
    end
end
end
%%
%% 
clc
clear
im = imread('lenna512.bmp');
result = average(im);
%% 
function [p1,p2,p3,p4] = average(im)
im = double(im);
[m, n] = size(im);

%for 2*2
e1 = zeros(m, n);
p1 = zeros(m, n);

% Start predict
for k = 2:m-1
    for s = 2:n-1
        p1(k,s) = (im(k,s-1)+im(k-1,s)+im(k-1,s-1)) / 3;
        e1(k,s) = im(k,s)- p1(k,s);
    end
end

% Edge filling
p1(1, :) = [];
p1(511, :) = [];
p1(:, 1) = [];
p1(:, 511) = [];
p1 = padarray(p1, [1 1], 'replicate');

%for 3*3
e2 = zeros(m, n);
p2 = zeros(m, n);

% Start predict
for k = 3:m-2
    for s = 3:n-2
        p2(k,s) = (im(k,s-1)+im(k-1,s)+im(k-1,s-1)+im(k-2,s)+im(k-2,s-1)+im(k-2,s-2)+im(k-1,s-2)+im(k,s-2)) / 8;
        e2(k,s) = im(k,s)- p2(k,s);
    end
end

% Edge filling
p2(1:2, :) = [];
p2(509:510, :) = [];
p2(:, 1:2) = [];
p2(:, 509:510) = [];
p2 = padarray(p2, [2 2], 'replicate');


%for weighted 2*2
e3 = zeros(m, n);
p3 = zeros(m, n);

% Start predict
for k = 2:m-1
    for s = 2:n-1
        p3(k,s) = (3*im(k,s-1)+3*im(k-1,s)+2*im(k-1,s-1)) / 8;
        e3(k,s) = im(k,s)- p3(k,s);
    end
end
% Edge filling
p3(1, :) = [];
p3(511, :) = [];
p3(:, 1) = [];
p3(:, 511) = [];
p3 = padarray(p3, [1 1], 'replicate');

%for weighted 3*3
e4 = zeros(m, n);
p4 = zeros(m, n);

% Start predict
for k = 3:m-2
    for s = 3:n-2
        p4(k,s) = (3*im(k,s-1)+3*im(k-1,s)+2*im(k-1,s-1)+2*im(k-2,s)+im(k-2,s-1)+im(k-2,s-2)+im(k-1,s-2)+2*im(k,s-2)) / 15;
        e4(k,s) = im(k,s)- p4(k,s);
    end
end

% Edge filling
p4(1:2, :) = [];
p4(509:510, :) = [];
p4(:, 1:2) = [];
p4(:, 509:510) = [];
p4 = padarray(p4, [2 2], 'replicate');

figure;

subplot(2,3,1),imshow(uint8(im));title('Original image');
subplot(2,3,2),imshow(p1,[]);title('Average image for 2x2');
subplot(2,3,3),imshow(p2,[]);title('Average image for 3x3');
subplot(2,3,4),imshow(p3,[]);title('Weighted Average image for 2x2');
subplot(2,3,5),imshow(p4,[]);title('Weighted Average image for 3x3');

imwrite(uint8(p1),'p1.bmp');
imwrite(uint8(p2),'p2.bmp');
imwrite(uint8(p3),'p3.bmp');
imwrite(uint8(p4),'p4.bmp');
end

%%4
%draft
clc
clear

im_plate = imread('car_license_plate.bmp');
im_template = imread('alphanumeric_templates.bmp');

str = detect_car_license_plate(im_plate,im_template);
%% this is the morphonical function
function [str]=detect_car_license_plate(im_plate,im_template)

%image binarization
%the license
im_plate = rgb2gray(im_plate);
im_plate = 255*double(im_plate>200); 
im_plate = im_plate(60:150,70:415); 
%figure, imshow(im_license)

% template
im_template = rgb2gray(im_template);
im_template = 255*double(im_template>200); 
%invert the image becasuse in morphological operations,
%the background of all the images should be set to black
im_template = 255-im_template; 
%figure, imshow(im_tem)

% erosion section
%the structure element
SE = strel('disk', 1, 0);
% erode the templates
im_template_erode = imerode(im_template, SE);
%figure, imshow(im_tem_erode)
%split out each characters as masks
k = 0;
for i = 1:79:426
for j = 1:80:426
 k = k+1;
 masks(1:80,1:81,k) = im_template_erode(i:i+79,j:j+80)/255; 
 %figure, imshow(mask(:,:,k))
end
end

% fit the masks in the erosion operation
X = []; 
A = 1:36;
X_string = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'];
X_index = []; % store the index of A_string
for i = 1:36 % character cases
 [a,b] = find(masks(:,:,A(i))==1); % find the row and column of the value equals 1
 % find the range of the characteristic, up, down, left, right
 row_up = min(a);
 row_down = max(a);
 column_left = min(b);
 column_right = max(b);
 se = masks(row_up-1:row_down+1,column_left-1:column_right+1,A(i)); 
 % store the se as a variable
 %figure, imshow(se); 
 
 % erosion
 imo = imerode(im_plate,se);
 imo_1 = bwmorph(imo, 'shrink', inf);
 %figure, imshow(imo_1)
 [~,x] = find(imo_1==1);
 for j = 1:size(x,1)
 if ~isempty(x(j))
 X_index = [X_index,i]; % the corresponding characters of the position
 end
 X = [X,x(j)];
 end
end

X_final = sort(X);
% find the correspondence of value
str = [];
for i = 1:size(X_final,2)
for j = 1:size(X,2)
 if X_final(i) == X(j)
 Index = X_index(j);
 end
end
 str = [str,X_string(Index)];
end
str = str(47:end);
disp('the license number is')
disp(str)
end

%%
%4
%improved
clc
clear

im_plate = imread('car_license_plate.bmp');
im_template = imread('alphanumeric_templates.bmp');

str = detect_car_license_plate(im_plate,im_template);
%% this is the morphonical function
function [str]=detect_car_license_plate(im_plate,im_template)

%image binarization
%the license
im_plate = rgb2gray(im_plate);
im_plate = 255*double(im_plate>200); 
im_plate = im_plate(60:150,70:415); 
%figure, imshow(im_license)

% template
im_template = rgb2gray(im_template);
im_template = 255*double(im_template>200); 
%invert the image becasuse in morphological operations,
%the background of all the images should be set to black
im_template = 255-im_template; 
%figure, imshow(im_tem)

% erosion section
%the structure element
SE = strel('disk', 1, 0);
% erode the templates
im_template_erode = imerode(im_template, SE);
%figure, imshow(im_tem_erode)
%split out each characters as masks
k = 0;
for i = 1:79:426
for j = 1:80:426
 k = k+1;
 masks(1:80,1:81,k) = im_template_erode(i:i+79,j:j+80)/255; 
 %figure, imshow(mask(:,:,k))
end
end

% fit the masks in the erosion operation
X_A = []; % not include the special case
A = [1,2,4,5,7,8,11,13,14,16:36];
A_string = ['A','B','D','E','G','H','K','M','N','P','Q','R','S','T','U','V','X','Y','Z','W','1','2','3','4','5','6','7','8','9','0'];
A_index = []; % store the index of A_string
for i = 1:30 % A to H
 [a,b] = find(masks(:,:,A(i))==1); % find the row and column of the value equals 1
 % find the range of the characteristic, up, down, left, right
 row_up = min(a);
 row_down = max(a);
 column_left = min(b);
 column_right = max(b);
 se = masks(row_up-1:row_down+1,column_left-1:column_right+1,A(i)); 
 % store the se as a variable
 %figure, imshow(se); 
 
 % erosion
 imo = imerode(im_plate,se);
 imo_1 = bwmorph(imo, 'shrink', inf);
 %figure, imshow(imo_1)
 [~,x] = find(imo_1==1);
 for j = 1:size(x,1)
 if ~isempty(x(j))
 A_index = [A_index,i]; % the corresponding characters of the position
 end
 X_A = [X_A,x(j)];
 end
end
% character C,F,I,J,L,O (3,6,9,10,12,15)
X_B = []; % this is for specail case
B = [3,6,9,10,12,15];
B_string = ['C','F','I','J','L','O'];
B_index = []; % store the index of A_string
temp = X_A; % this is for iterative comparing with the postion
for k = 1:6 % k is the index of special characters
 [a,b] = find(masks(:,:,B(k))==1); % find the row and column of the value equals 1
 % find the range of the characteristic, up, down, left, right
 row_up = min(a);
 row_down = max(a);
 column_left = min(b);
 column_right = max(b);
 se = masks(row_up-1:row_down+1,column_left-1:column_right+1,B(k)); 
 % store the se as a variable
 %figure, imshow(se); 
 imo = imerode(im_plate,se);
 imo_1 = bwmorph(imo, 'shrink', inf); % shrink to 1 point
 %figure, imshow(imo_1)
 [~,x] = find(imo_1==1);
 % find all the distance
for j = 1:size(x,1)
 D = [];
 for i = 1:size(temp,2) % compared with normal case
 D = [D, abs(x(j)-temp(i))];
 end
 if min (D)<15 % if the distance is very small, it just one character
 X_B = X_B;
 else
 if x <size(im_plate,2)-5 & x > 5
 temp = [temp,x(j)];
 X_B = [X_B,x(j)];
 B_index = [B_index,k]; % if it is the true character, then add the index
 else
 X_B = X_B;
 end
 end
end
end
X = [X_A,X_B];
X_index = [A_index,B_index+30]; % B should have a DC value
X_string = [A_string, B_string];
X_final = sort(X);
% find the correspondence of value
str = [];
for i = 1:size(X_final,2)
for j = 1:size(X,2)
 if X_final(i) == X(j)
 Index = X_index(j);
 end
end
 str = [str,X_string(Index)];
end
str = str(31:end);
disp('the license number is')
disp(str)
end

